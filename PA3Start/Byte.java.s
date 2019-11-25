    .file  "main.java"
__SREG__ = 0x3f
__SP_H__ = 0x3e
__SP_L__ = 0x3d
__tmp_reg__ = 0
__zero_reg__ = 1
    .global __do_copy_data
    .global __do_clear_bss
    .text
.global main
    .type   main, @function
main:
    push r29
    push r28
    in r28,__SP_L__
    in r29,__SP_H__
/* prologue: function */
    call _Z18MeggyJrSimpleSetupv
    /* Need to call this so that the meggy library gets set up */
    
#Integer
ldi    r24,lo8(1)
ldi    r25,hi8(1)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
#Integer
ldi    r24,lo8(2)
ldi    r25,hi8(2)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
ldi    r22,7
push   r22
pop r20
pop r22
pop r24
call _Z6DrawPxhhh
call _Z12DisplaySlatev
ldi r25, hi8(54485)
ldi r24, lo8(54485)
push r25
push r24
#Integer
ldi    r24,lo8(2)
ldi    r25,hi8(2)
push   r25
push   r24
pop r22
pop r23
pop r24
pop r25
call _Z10Tone_Startjj


/* epilogue start */
    endLabel:
    jmp endLabel
    ret
    .size   main, .-main

