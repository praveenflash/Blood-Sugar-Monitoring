start:
again:	clr p3.2
wait:   jnb p3.2,wait
wait1:  jb p3.2,wait1
	mov a,p1
	mov b,a
wait2:   jnb p3.2,wait2
wait3:  jb p3.2,wait3
	mov a,p1
	clr c
	rlc a
	rlc a
	rlc a
	rlc a
	orl a,b
	sjmp send
       
send:   mov TMOD,#20H
        
        mov SCON,#50H
        
        mov TH1,#0FDH
        mov TL1,#0FDH
        
        setb TR1

Serial_Send: jnb TI,Serial_Send
       
        clr TI
        
        mov SBUF,A
        sjmp again
end
        