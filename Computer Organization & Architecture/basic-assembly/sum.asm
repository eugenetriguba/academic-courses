; Compute sum=158+number+number1, where number is initialed to -105

.586
.MODEL FLAT

.STACK  4096            	; reserve 4096-byte stack

.DATA                   	; reserve storage for data
number  DWORD   -105
number1 DWORD	12
sum     DWORD   ?

.CODE                           ; start of main program code
main    PROC
        mov     eax, number     ; move doubleword number to EAX
        add     eax, 158        ; add doubleword 158 to EAX
		add		eax, number1	; add doubleword number1 to EAX
        mov     sum, eax        ; move EAX to doubleword sum 
        mov   	eax, 0          ; exit with return code 0
        ret
main    ENDP

END                             ; end of source code