; Pack April 15, 2016 into dx using the following format:
; Year: bits 9-15
; Month: bits 5-8
; Day: bits 0-4
; dx (16 bit) = dh (bits 15 - 8) and dl (bits 0-7)

.586
.MODEL FLAT
.STACK 4096

.data
day BYTE 15
month BYTE 4
year BYTE 16

.code
main PROC
    mov dx, 0		; reset dx

    mov dh, year	; copy year into dh
    
    mov ax, 0		; reseteax
    mov al, month	; copy month into lower 8 bits of ax
    shl al, 4		; shift al into lefthalf of ax
    OR dl, al		; copy al into lefthalf of dl

    shl edx, 1		; year and month are now in correct position

    mov ax, 0		; reset ax
    mov al, day		; copy day into lower 8 bits of ax
    OR dl, al		; put day into it's correct positon in dx

    mov eax, 0
    ret
main ENDP

END