# This script is created by NSG2 beta1
# <http://wushoupong.googlepages.com/nsg>

#===================================
#     Simulation parameters setup
#===================================
set val(stop)   10.0                         ;# time of simulation end

#===================================
#        Initialization        
#===================================
#Create a ns simulator
set ns [new Simulator]

#Open the NS trace file
set tracefile [open two.tr w]
$ns trace-all $tracefile

#Open the NAM trace file
set namfile [open two.nam w]
$ns namtrace-all $namfile

#===================================
#        Nodes Definition        
#===================================
#Create 6 nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

#===================================
#        Links Definition        
#===================================
#Createlinks between nodes
$ns duplex-link $n3 $n0 100.0Mb 10ms DropTail
$ns queue-limit $n3 $n0 50
$ns duplex-link $n1 $n3 100.0Mb 10ms DropTail
$ns queue-limit $n1 $n3 50
$ns duplex-link $n3 $n2 100.0Mb 10ms DropTail
$ns queue-limit $n3 $n2 50
$ns duplex-link $n3 $n4 100.0Mb 10ms DropTail
$ns queue-limit $n3 $n4 50
$ns duplex-link $n5 $n3 100.0Mb 10ms DropTail
$ns queue-limit $n5 $n3 50

#Give node position (for NAM)
$ns duplex-link-op $n3 $n0 orient left-up
$ns duplex-link-op $n1 $n3 orient left-down
$ns duplex-link-op $n3 $n2 orient right-up
$ns duplex-link-op $n3 $n4 orient left-down
$ns duplex-link-op $n5 $n3 orient left-up

#===================================
#        Agents Definition        
#===================================
#Setup a TCP connection
set p0 [new Agent/Ping]
$ns attach-agent $n0 $p0
$p0 set packetSize_ 10000

set p1 [new Agent/Ping]
$ns attach-agent $n1 $p1
$p1 set packetSize_ 10000

set p4 [new Agent/Ping]
$ns attach-agent $n4 $p4


Agent/Ping instproc recv {from rtt } {
$self instvar node_
puts "node [$node_ id] received ping answer from \
           $from with round-trip-time $rtt ms."
}
$ns connect $p0 $p4
$ns connect $p1 $p4



#===================================
#        Applications Definition        
#===================================
#Setup a FTP Application over TCP connection
$ns at 0.5 "$p0 send"
$ns at 0.6 "$p0 send"
$ns at 0.7 "$p0 send"
$ns at 0.8 "$p0 send"
$ns at 0.9 "$p0 send"
$ns at 1.0 "$p0 send"
$ns at 1.1 "$p0 send"
$ns at 1.2 "$p0 send"

$ns at 0.5 "$p1 send"
$ns at 0.6 "$p1 send"
$ns at 0.7 "$p1 send"
$ns at 0.8 "$p1 send"
$ns at 0.9 "$p1 send"
$ns at 1.0 "$p1 send"
$ns at 1.1 "$p1 send"
$ns at 1.2 "$p1 send"



#===================================
#        Termination        
#===================================
#Define a 'finish' procedure
proc finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile
    exec nam two.nam &
    exit 0
}
$ns at $val(stop) "$ns nam-end-wireless $val(stop)"
$ns at $val(stop) "finish"
$ns at $val(stop) "puts \"done\" ; $ns halt"
$ns run
