; Compute the sum of corresponding elements in two arrays 
; and deposit each sum into a third array

.586
.MODEL FLAT
.STACK 4096

.data
arr1 DWORD 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
arr2 DWORD 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
result DWORD ?

.code
main PROC
    mov ebx, OFFSET arr1			; ebx = address of arr1
    mov edx, OFFSET arr2			; edx = address of arr2
    mov eax, OFFSET result			; eax = address of result
    mov ecx, LENGTHOF arr1			; initialize loop counter

L1:
    mov edi, 0						; sum = 0
    add edi, [ebx]					
    add edi, [edx]					; sum = arr1 + arr2
    mov [eax], edi					; result = sum

    add ebx, TYPE arr1
    add edx, TYPE arr2
    add eax, TYPE result			; point to next element of arr1, arr2, and result
    loop L1

    mov eax, 0
    ret
main ENDP

END