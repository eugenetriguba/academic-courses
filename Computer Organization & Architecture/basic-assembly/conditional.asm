; Scans an array for the first non-zero value. If
; a non-zero value is found, esi is left pointing 
; at it. If no non-zero value is found, esi will
; point to the sentinel value 0 stored immediately
; after the array.

.586
.MODEL FLAT
.STACK 4096

.data
array DWORD 0, 0, 0, 0, 10, 50, 40, -50
sentinel DWORD 0

.code
main PROC
    mov esi, OFFSET array
    mov ecx, LENGTHOF array

    L1:
        cmp WORD PTR [esi], 0		; check for zero
        pushfd				        ; push flags on stack
        add esi, TYPE array		    ; (ADD modifies flags)
        popfd				        ; pop flags from stack
        loope L1			        ; loop if equal to 0
        jz quit			            ; none found
        sub esi, TYPE array		    ; esi is one too far, now points to value

    quit:
        mov eax, 0
        ret

main ENDP

END
