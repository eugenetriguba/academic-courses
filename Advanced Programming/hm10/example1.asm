; Eugene Triguba <ytriguba17@ole.augie.edu>
; Advanced Programming: Homework 10
;
; Evaluates the expression -(x + y - 2z + 1) 
; for double-word values stored in memory

.586
.MODEL FLAT
.STACK  4096

.DATA
x	DWORD	35
y	DWORD	47
z	DWORD   26
result	DWORD	?

.CODE
main    PROC   
        mov eax, x
        add eax, y
        mov result, eax		; result = x + y

        mov eax, z		; eax = z
        add eax, z		; eax = 2z

        sub result, eax		; result = (x + y) - 2z
        inc result		; result = (x + y - 2x) + 1
        neg result		; result = -(x + y - 2x + 1)

        mov   	eax, 0
        ret
main    ENDP

END
