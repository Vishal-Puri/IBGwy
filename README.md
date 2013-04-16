IBGwy
=====

Client to IB Socket API

Design
======

Event driven architecture using Google's EventBus https://code.google.com/p/guava-libraries/wiki/EventBusExplained. 


Components
===========

    OutBound

Acts as a IB gateway. Maintains a socket connection and dispatches all outgoing IB API calls.

    InBound

An IB EWrapper implementation. Receives all inbound messages from IB.

    EventBusWrapper

EventBus delegate including provide message sequencing.

    IBApiHelper

Bridge between our internal and IB types.

Usage Example:
==============
    Coming soon!



      

