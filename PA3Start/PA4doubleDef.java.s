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
    
ldi r24, lo8(0)
ldi r25, hi8(0)
call malloc
push r25
push r24
#Integer
ldi    r24,lo8(3)
ldi    r25,hi8(3)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
#Integer
ldi    r24,lo8(7)
ldi    r25,hi8(7)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
ldi    r22,5
push   r22
pop r18
#notdoingexp
pop r20
#notdoingexp
#notdoingexp
pop r24
pop r25
call C_setP


/* epilogue start */
    endLabel:
    jmp endLabel
    ret
    .size   main, .-main

   .text
.global C_setP
.type C_setP, @function
C_setP:
push r29
push r28
ldi r30, 0
push r30
push r30
push r30
push r30
push r30
in r28, __SP_L__
in r29, __SP_H__
std Y + 2, r25
std Y + 1, r24
std Y + 3, r22
std Y + 4, r20
std Y + 6, r18
ldd r24, Y + 3
push r24
ldd r24, Y + 4
push r24
ldd r24, Y + 5
push r24
pop r20
pop r22
pop r24
call _Z6DrawPxhhh
call _Z12DisplaySlatev
pop r30
pop r30
pop r30
pop r30
pop r30
pop r28
pop r29
ret
.size C_setP, .-C_setP
