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
set tracefile [open 3.tr w]
$ns trace-all $tracefile

#Open the NAM trace file
set namfile [open 3.nam w]
$ns namtrace-all $namfile

#===================================
#        Nodes Definition        
#===================================
#Create 5 nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]

#===================================
#        Links Definition        
#===================================
#Createlinks between nodes
$ns duplex-link $n0 $n1 100.0Mb 10ms DropTail
$ns queue-limit $n0 $n1 50
$ns duplex-link $n2 $n1 10.0Mb 10ms DropTail
$ns queue-limit $n2 $n1 5
$ns duplex-link $n1 $n3 100.0Mb 10ms DropTail
$ns queue-limit $n1 $n3 20
$ns duplex-link $n1 $n4 10.0Mb 10ms DropTail
$ns queue-limit $n1 $n4 5

#Give node position (for NAM)
$ns duplex-link-op $n0 $n1 orient right-down
$ns duplex-link-op $n2 $n1 orient left-down
$ns duplex-link-op $n1 $n3 orient left-down
$ns duplex-link-op $n1 $n4 orient right-down

#===================================
#        Agents Definition        
#===================================
#Setup a TCP connection
set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0
set sink1 [new Agent/TCPSink]
$ns attach-agent $n2 $sink1
$ns connect $tcp0 $sink1
$tcp0 set packetSize_ 1500

#Setup a TCP/Reno connection
set tcp3 [new Agent/TCP/Reno]
$ns attach-agent $n3 $tcp3
set sink2 [new Agent/TCPSink]
$ns attach-agent $n4 $sink2
$ns connect $tcp3 $sink2
$tcp3 set packetSize_ 1500


#===================================
#        Applications Definition        
#===================================
#Setup a FTP Application over TCP/Reno connection
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp3
$ns at 1.0 "$ftp0 start"
$ns at 2.0 "$ftp0 stop"
set f1 [open f1.tr w]
$tcp0 attach $f1
set f2  [open f2.tr w]
$tcp3 attach $f2
$tcp0 trace cwnd_
$tcp3 trace cwnd_

#Setup a FTP Application over TCP connection
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp0
$ns at 1.0 "$ftp1 start"
$ns at 2.0 "$ftp1 stop"


#===================================
#        Termination        
#===================================
#Define a 'finish' procedure
proc finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile
    exec nam 3.nam &
    exit 0
}
$ns at $val(stop) "$ns nam-end-wireless $val(stop)"
$ns at $val(stop) "finish"
$ns at $val(stop) "puts \"done\" ; $ns halt"
$ns run
