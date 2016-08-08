<!-- the line below needs to be an empty line C: (its because kramdown isnt
     that smart and dearly wants an empty line before a heading to be able to
     display it as such, e.g. website) -->

# Control system toolbox

##Continuous
Derivative      Output time derivative of input
Integrator      Limited  Integrate signal
PID             Simulate continuous PID controllers
TransferFcn     Model linear system by transfer function
TransportDelay  Delay input by given amount of time
PT1             First order lowpass filter

##Discontinuities
Saturation Limit range of signal
Backlash   Model behavior of system with play
DeadZone   Provide region of zero output
Relay      Switch output between two constants
Min        Get minimum from input signals
Max        Get maximum from inpur signals

##Math Operations
Sign     Indicate sign of input
Sqrt     Calculate square root
Abs      Output absolute value of input
Gain     Multiply input by constant
Product  Multiply one input by another
Sum      Sum inputs
Divide   Divide one input by another
Subtract Subtract inputs

##Logic Operations

##Sources
Pulse   Generate square wave pulses at regular intervals
Ramp    Generate constantly increasing or decreasing signal
Random  Generate normally distributed random signal
Step    Generate step function
Sin     Generate sin function

##Monitors
ConsoleTracer   Write data to console
MemoryTracer    Write data to memory
XMLTracer       Write data to xml

