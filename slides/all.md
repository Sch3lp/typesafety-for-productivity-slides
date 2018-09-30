## TypeSafety for Productivity

_How Types can increase developer productivity_

|>

### Context: A Booking webapp

Rewrite of a legacy application to handle dinner table bookings

Actual handling still in legacy, data entry in web-app

|>

Booking code is entered in a text input field

|>

Customer complaint: Booked a table, but no confirmation

|>

Legacy logs show error in booking code <!-- .element: class="fragment" data-fragment-index="2" -->

    ERROR: booking code is too long (14)
    ERROR: booking code is non numerical

<|

### Example 1

Let's find the bug

|>

Think about <i>when</i> we encounter the bug, and how it relates to our webapp lifecycle.

At what point in the flow does the error happen? <!-- .element: class="fragment" data-fragment-index="2" -->

That's right. It doesn't. It happens outside our application. <!-- .element: class="fragment" data-fragment-index="3" -->

<|

### Example 2

Advance point of failure to our web application

|>

Introduced domain validation on a `Booking`.

Let's see what code breaks in tests.  <!-- .element: class="fragment" data-fragment-index="2" -->

|>

At what point in the flow does the error happen this time?

Good enough? <!-- .element: class="fragment" data-fragment-index="2" -->

<|

### Example 3

Advance point of failure to as soon as it enters our application: in the REST api class.

Let's introduce a Wrapper object BookingCode and make it compile. <!-- .element: class="fragment" data-fragment-index="2" -->

|>

Let's see what tests break this time.

|>

At what point in the flow does the error happen this time?

How do we prevent these kinds of bugs in the future? <!-- .element: class="fragment" data-fragment-index="2" -->

Think we can learn about similar bugs faster? <!-- .element: class="fragment" data-fragment-index="3" -->

<|

### Foray into developer laziness

There are two kinds of laziness: the right kind and the wrong kind

|>

#### Wrong kind of laziness
The wrong kind of laziness cuts corners.

Instead of writing a Wrapper class, Strings or other primitives are passed as arguments.

Sure you can write code very fast, but at the cost of explicitness. <!-- .element: class="fragment" data-fragment-index="2" -->

It's not intention revealing (1 of the 4 rules of simple design). <!-- .element: class="fragment" data-fragment-index="2" -->

|>

In the end this costs time and productivity: every time you write a new method that is passed a primitive, you wonder what possible values this primitive can hold, and what edge cases you're supposed to take into account.

|>

Current "you" is lazy

|>

#### Right kind of laziness

The right kind of laziness spends just a little extra time writing a Wrapper class.

Instead of thinking what possible values a primitive can hold, you think about this once and you make those rules explicit in that Wrapper class. <!-- .element: class="fragment" data-fragment-index="2" -->

Now every time this is passed into a method you no longer need to think what possible values it can be: it can only be one type with one set of rules. <!-- .element: class="fragment" data-fragment-index="2" -->

|>

Future "you" is lazy

|>

#### No need to take long
Writing a new Wrapper class also doesn't need to take long.

Let me show you.

<|

### Example 4
#### The curious case we missed

This is me gambling you didn't pay too much attention :)

|>

Who can tell me what we missed? More specifically in the Booking. <!-- .element: class="fragment" data-fragment-index="2" -->

Eventhough BookingCode can be null, our Booking always expects there to be one. <!-- .element: class="fragment" data-fragment-index="3" -->

Thanks for NPE's Java! <!-- .element: class="fragment" data-fragment-index="4" -->

|>

### I guess we fix it with Optional?

<|

### Java's Optional

Optional **does** express that some property might not exist.

But it's not very easy to work with.

And it's not serializable.

And you're not allowed to actually **use** it as a field.

Or use it as a parameter for that matter.

This really just makes Optional a glorified if-statement.

<|

### There's gotta be a better way!

And there is! <!-- .element: class="fragment" data-fragment-index="2" -->

<|

# Kotlin! <3

|>

## Optional

Kotlin has the concept of optionality built-in

val code: BookingCode  //non optional <!-- .element: class="fragment" data-fragment-index="2" -->

val code: BookingCode? // optional <!-- .element: class="fragment" data-fragment-index="3" -->

|>

## Immutability

Kotlin also has the concept of immutability built-in:

data class BookingCode(val code: String) <!-- .element: class="fragment" data-fragment-index="2" -->

No longer do you have to write tedious equals() and hashcode() reflection-equals implementations! <!-- .element: class="fragment" data-fragment-index="3" -->

|>

## Closed by default

You ever really tried to prevent access to methods or classes of an Aggregate in Java?

Here's how you do it in Kotlin:

class Booking() <!-- .element: class="fragment" data-fragment-index="2" -->

In Kotlin if you want a public class or method you'll have to add the "open" modifier to it <!-- .element: class="fragment" data-fragment-index="3" -->

|>

## Named Parameters

Kotlin also has named parameters, essentially removing the need for TestBuilders

val booking: Booking = Booking(bookingCode = BookingCode("87654321"), name = "snarf") <!-- .element: class="fragment" data-fragment-index="2" -->

|>

## Extension functions

Kotlin also has extension functions, replacing "defaults" in TestBuilders

fun BookingCode.Companion.defaultBookingCode() = <!-- .element: class="fragment" data-fragment-index="2" -->

BookingCode("87654321") <!-- .element: class="fragment" data-fragment-index="2" -->

|>

## Sealed classes (aka traits, aka union types)
Kotlin also has "sealed classes" allowing you to be more expressive with your types.

|>

## Sealed classes

Given this Sealed Class:

    sealed class RoverCommand
    data class Forward(val steps: Int): RoverCommand()
    data class Backward(val steps: Int): RoverCommand()
    object Left: RoverCommand() //these don't take arguments
    object Right: RoverCommand() //these don't take arguments

|>

You can do pattern matching:

    fun handle(cmd: RoverCommand) = when(cmd) {
        is Forward -> moveForward(cmd.steps)
        is Backward -> moveBackward(cmd.steps)
        is Left -> turnLeft()
        is Right -> turnRight()
    }

|>

This won't compile:

    fun handle(cmd: RoverCommand) = when(cmd) {
        is Forward -> moveForward(cmd.steps)
        is Left -> turnLeft()
        is Right -> turnRight()
    }

The compiler will tell you you're missing the case of Backward

|>

This is about as "early" as one can get into knowing when a bug would happen.

<|

# Conclusion

Not using Types **costs** time, so:

* Write Wrapper classes (from the start!) <!-- .element: class="fragment" data-fragment-index="2" -->
* Kotlin has a set of features that can help with that  <!-- .element: class="fragment" data-fragment-index="3" -->
* Use Sealed classes whenever you can to learn asap about potential bugs <!-- .element: class="fragment" data-fragment-index="4" -->

Thanks! <!-- .element: class="fragment" data-fragment-index="5" -->