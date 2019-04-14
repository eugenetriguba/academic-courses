; Multiply eax by 26, using only shifting and addition instructions

.586
.MODEL FLAT
.STACK 4096

.data
startingValue DWORD 2

.code
main PROC
    mov eax, startingValue
    mov ebx, startingValue
    mov ecx, startingValue

    shl eax, 4
    shl ebx, 3
    shl ecx, 1

    add eax, ebx
    add eax, ecx				; eax = startingValue * (2^4 + 2^3 + 2^1)

    mov eax, 0
    ret
main ENDP

END