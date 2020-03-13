# Introduction to Programming 2

## How To Manipulate Objects

If we were just making objects and not doing anything with them, programming would be useless. The way we manipulate objects is by using different statements to look for something specific to happen or we can use a function \(also known as a method\) to manipulate the object.  On this page i'll do a brief overview of both.

## IF, ELSE IF, ELSE Statements

```java
public class Main(){

public static void main(String[] args){
int x = 0;
boolean y = true;

// an if statement measures if a certain statement is true or not.
// this can be done with both booleans and comparing objects
if(x == 0){
// Keep in mind the difference between the assignment operator "="
// and the compare operator "==". 
y = false;
}
// you can also compare booleans 
if(y == true){
x=1;
}
// There are other logical operators such as the AND Operator "&&" and the 
// OR Operator "||". AND checks if both statements are true while OR checks for one
if(x == 0 && y == true){
x = 0;
}







```

You can also use extensions to these if statements to check for multiple outcomes. These extensions are the ELSE IF statement and the ELSE statement.

{% code title="public class Main{" %}
```java
public static void main(String[] args){
int x = 0;

if(x==1)
{
// DO SOMETHING
}
else if(x == 2){
// DO SOMETHING
}
else{
// DO SOMETHING
}


}

```
{% endcode %}

{% hint style="info" %}
NOTICE: Else if statements function the same way as an if statement but its only checked for if the statement above did not execute. 

Also note that Else statements do NOT have condition to execute because they will only run if everything else doesn't execute.
{% endhint %}

## Functions/Methods

A majority of the way we manipulate objects in FRC Java is through the use of functions. Every object has its own set of functions or things that it can do. For example, a car object might have a Drive function to make it drive or a OilChange function to change the oil. Take a look at the example below.

{% hint style="info" %}
This is a Car class that asks for 4 parameters\(4 motors\) to be passed in when a car object is created. The drive function at the bottom sets the 4 motors speed to the number the function asks for when its used
{% endhint %}

```java
public class Car{
private driveMotor d1;
private driveMotor d3;
private driveMotor d2;
private driveMotor d4;

public Car(driveMotor d1,driveMotor d2,driveMotor d3,driveMotor d4){
this.d1 = d1;
this.d2 = d2;
this.d3 = d3;
this.d4 = d4;
}

public void Drive(double speed){
d1.set(speed);
d2.set(speed);
d3.set(speed);
d4.set(speed);
}

public static void main(String[] args){
// Creates a new Car object, asks for 4 parameters.
// Assumes that d1-d4 are all instantiated and defined
Car car = new Car(d1,d2,d3,d4);
car.drive(.2);
}}
```

{% hint style="info" %}
At the bottom here in main \(our executable\) we create a car object and use the drive function to make it go at a speed of .2
{% endhint %}

