## Example 2

Throwing a DomainValidationRuntimeException when a Booking is trying to be created with an erroneous booking code.

This shows a developer what rules there are regarding a Booking, but the problem remains that the bug/exception ends up happening too late.  
And we're still passing booking code around as a String to methods etc. which really gives no information to developers working on this code.