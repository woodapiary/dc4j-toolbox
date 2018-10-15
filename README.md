<!-- the line below needs to be an empty line C: (its because kramdown isnt
     that smart and dearly wants an empty line before a heading to be able to
     display it as such, e.g. website) -->

# Control system toolbox


##1. Continuous
* **Derivative** - output time derivative of input.
* **Integrator** - limited  integrate signal.
* **PID** - simulate continuous PID controllers.
* **TransportDelay** - delay input by given amount of time.
* **PT1** - first order lowpass filter.

##2. Discontinuities
* **Saturation** - limit range of signal.
* **Backlash** - model behavior of system with play.
* **DeadZone** - provide region of zero output.
* **Relay** - switch output between two constants.
* **Min** - get minimum from input signals.
* **Max** - get maximum from inpur signals.

##3. Math Operations
* **Sign** - indicate sign of input.
* **Sqrt** - calculate square root.
* **Abs** - output absolute value of input.
* **Gain** - multiply input by constant.
* **Product** - multiply one input by another.
* **Sum** - sum inputs.
* **Divide** - givide one input by another.
* **Subtract** - subtract inputs.

##4. Logic Operations

##5. Sources
* **Pulse** - generate square wave pulses at regular intervals.
* **Ramp** - generate constantly increasing or decreasing signal.
* **Random** - generate normally distributed random signal.
* **Step** - generate step function.
* **Sin** - generate sin function.

##6. Monitors
* **ConsoleTracer** - write data to console.
* **MemoryTracer** - write data to memory.

