; Read 2 numbers, output the sum and difference

.586
.MODEL FLAT
INCLUDE io.h
        
.STACK 4096

.DATA 
num1 			DWORD   	?
num2 			DWORD   	?
sum				DWORD		0
diff			DWORD		?
prompt1 		BYTE    	"Enter first number", 0
prompt2 		BYTE    	"Enter second number", 0
inputBuf  		BYTE    	40 DUP (?)			
resultSumLbl 	BYTE  		"The sum is", 0
resultDiffLbl 	BYTE  		"The difference is", 0
outputBuf		BYTE    	11 DUP (?), 0		

.CODE
_MainProc PROC
        input   prompt1, inputBuf, 40    	; inputBuf=input ASCII characters	
        atod    inputBuf  					; eax= (integer) inputBuf
        mov     num1, eax					; num1 = eax

        input   prompt2, inputBuf, 40      
        atod    inputBuf  
        mov     num2, eax					; num2 = eax
        
        mov     eax, num1;
        add     eax, num2; 
        mov		sum, eax;					; sum = num1 + num2

        mov eax, num1;
        sub eax, num2;
        mov diff, eax;						; diff = num1 - num2

        dtoa    outputBuf, sum        		; outputBuf = (String) sum
        output  resultSumLbl, outputBuf

        dtoa    outputBuf, diff        		; outputBuf = (String) diff
        output  resultDiffLbl, outputBuf

        mov     eax, 0                      ; exit with return code 0
        ret
_MainProc ENDP
END
