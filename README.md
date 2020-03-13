---
description: An overview of what the control sub-team entails.
---

# Introduction to Programming 1

## What is Java?

Java is one of the main programming languages used in FRC for its simplicity and it's support. It's a object-oriented programming language meaning that Java works with objects. Objects can be concrete things such as cars,robots,toothbrushes or they can be more abstract things like numbers or true/false statements.  :

```java
public class Main{

public static void main(String[] args){
int number = 0;
double decimal = .5;
boolean state = true;

Car name_of_car = new Car();
Robot eileen = new Robot();
}}
```

{% hint style="info" %}
 All code that runs has to be within a class. A class can be thought of as the body for your program and it has the exact same name as the file its in.

eg: DriveTrain.java has a class name DriveTrain.
{% endhint %}

This can divide objects into one of two categories. Primitives and non-primitives, primitives are obviously native and universal in Java while non-primitives can be invented and created by someone else. The real question is, why does this matter? Primitives are instantiated \(given a name and type\) and declared \(given a value of some sort\) in a different way than non Primitives. Check below:

```java
public class Main{

public static void main(String[] args){ 
// These are code comments, they are seperate from the code
// and can be used to write notes.

/*You can instantiate a primitive such as an int(integer)
* by giving it a type, a name, and then using the assignment
* operator "=" to give it a value.
*/
 //  TYPE   NAME    OPERATOR  VALUE   ENDOFLINE
     int    number      =       0        ;
/* The nice thing about Java is it really doesnt care about spacing
*  and its possible to write all your code on one huge line but something
*  it does care about it semicolons, this is the way it knows you have ended
*  your statement. Another thing it cares about is grouping symbols such as 
*  {} brackets or () parentheses. I'll get into how to use these later.  
*/ 


// SORRY THERES MORE 

/* Instantiating a non-primitive is a little bit more confusing but
*  still isn't too bad. If we were to write a class for a drivetrain,
*  what kind of things does it need? Does it need a speed? Maybe a motor?
*  these things can be passed in as parameters( A neccesary element that
*  the code is asking for.) 
*/

//  TYPE    NAME  OPERATOR  NEW  TYPE   PARAMETERS                ENDOFLINE
   DriveTrain drive     =   new DriveTrain(double speed, Motor motor)  ;



}}
```

{% hint style="info" %}
There are many keywords in Java such as "class" and "new". new literally means that we are creating a new object.
{% endhint %}

