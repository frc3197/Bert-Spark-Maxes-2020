---
description: This section details how to write a class.
---

# Introduction to Programming 3

## Writing Classes

Classes can be thought of in most cases like blueprints for objects that we use. Writing classes can generally be broken up into 3 parts : Instance Variables, Constructor, Functions. 

## Instance Variables

Starting with Instance Variables they're exactly as the name implies. They are variable created for every instance of the object. For example, if I had two car objects\(car1 and car2\) they would each have separate speeds, horsepower, engines, etc. despite both being cars. These are instantiated at the top of the class but are NOT assigned a value unless they are constants that never change. They are almost always going to be private variables meaning they can't be accessed by another class. This is because we are going to be using functions to manipulate the instance variables.

```java
public class Car{
private double speed;
private double horsepower;
private Engine engine;
```

{% hint style="info" %}
NOTICE: Instance variables don't have to be primitives they can be really any object.
{% endhint %}

## Constructor

The next part of a class is the constructor. Again, as the name implies it is the way we will construct an object of that class. Keep in mind it shares the name of the class because a Car class should not make a banana object it should create a Car object. Any parameters passed in should be stored in an instance variable. Because it needs to know what to do with the parameters passed in. The following code example is an extension of the example above.

{% hint style="info" %}
One cool thing to note is you can have multiple constructors with the parameters in different orders, different parameters, maybe even no parameters. But keep in mind you still have to assign a value to all the instance variables if you wish to use them
{% endhint %}

```java
public Car(double speed, double horsepower, Engine engine){
// This asks for 2 doubles and an engine object to be passed in when creating 
// the object. Notice the order, because these are just variable names Java can't
// distinguish between the two and you have to point it to the instance variable
this.speed = speed;
this.horsepower = horsepower;
this.engine = engine;
// The keyword "this" points to the instance variables of that class so it will
// assign the value of the parameters passed in to the instance variables
}

public Car(){
speed = 1;
//speed is set to 1 if no parameters are given
horsepower = 50;
//horsepower is set to 50 if no parameters are given
engine = new Engine();
// a new engine object is created to store in the instance variable engine
}


```

## Functions

I briefly touched how we use functions in the last page but I never explained how to write them. Once you begin to write these more it becomes very easy. The first thing to point out is that unless specified, all methods will return a variable. If we had a function for addition we would want it to give us the result. This is done using the _return_ keyword. Look at the example below.

```java
public double addition(double x, double y){
return x + y;
}

// In a seperate class we can use this value by calling the function.
double x = 1;
double y = 8;
double result = addition(x,y);
// If we were to print out the value of result it would be 9.0
```

Now that we understand what the return keyword does, we can begin to write functions. The syntax is very particular in that you need to state if the method will be public or private, you need to give it a return type, give the method a name, and state its parameters. Ill go more in depth in the example

```java
// ACCESS  RETURN TYPE FUNCTION NAME         PARAMETERS   
  public      Car        buildCar    (Engine engine ,Wheels wheel){
return new Car(engine, wheel);
}
```

{% hint style="info" %}
If the method does not return a value and is only used to do something then we can set the return type to VOID meaning that java does not expect anything to be returned.
{% endhint %}

```java
public void drive(Car car){
car.drive(.6);
}
```



