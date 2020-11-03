; Eugene Triguba <ytriguba17@ole.augie.edu>
; Advanced Programming: Homework 9
;
; Compute sum = 158 + number + number1, 
; where number is initialed to -105

.586
.MODEL FLAT
.STACK 4096

.DATA
number  DWORD -105
number1 DWORD 12
sum     DWORD ?

.CODE
main    PROC
        mov     eax, [number]     ; move number into EAX
        add     eax, 158          ; add 158 to EAX
        add	    eax, [number1]    ; add number1 to EAX
        mov     sum, eax          ; move EAX to sum 
        mov   	eax, 0            ; exit with return code 0
        ret
main    ENDP

END