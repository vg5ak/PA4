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
ldi    r24,lo8(4)
ldi    r25,hi8(4)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
#Integer
ldi    r24,lo8(4)
ldi    r25,hi8(4)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
ldi    r22,1
push   r22
pop r20
pop r22
pop r24
call _Z6DrawPxhhh
call _Z12DisplaySlatev
#IFStatement
ldi r22, 1
push r22
pop r24
ldi r22, 1
eor r24, r22
push r24
pop    r24
ldi    r25, 0
cp     r24, r25
brne MJ_L1
jmp MJ_L0
MJ_L1:
#Integer
ldi    r24,lo8(0)
ldi    r25,hi8(0)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
#Integer
ldi    r24,lo8(0)
ldi    r25,hi8(0)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
ldi    r22,1
push   r22
pop r20
pop r22
pop r24
call _Z6DrawPxhhh
call _Z12DisplaySlatev
#Integer
ldi    r24,lo8(7)
ldi    r25,hi8(7)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
#Integer
ldi    r24,lo8(0)
ldi    r25,hi8(0)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
ldi    r22,4
push   r22
pop r20
pop r22
pop r24
call _Z6DrawPxhhh
call _Z12DisplaySlatev
jmp MJ_L2
MJ_L0:
#IFStatement
call    _Z16CheckButtonsDownv
lds r24, Button_Up
tst r24
breq MJ_L6
MJ_L7:
ldi r24, 1
jmp MJ_L8
MJ_L6:
MJ_L8:
push r24
pop    r24
ldi    r25, 0
cp     r24, r25
brne MJ_L4
jmp MJ_L3
MJ_L4:
#Integer
ldi    r24,lo8(0)
ldi    r25,hi8(0)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
#Integer
ldi    r24,lo8(0)
ldi    r25,hi8(0)
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
#Integer
ldi    r24,lo8(2)
ldi    r25,hi8(2)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
#MeggyGetPixel
pop    r22
pop    r24
call   _Z6ReadPxhh
push   r24
pop r20
pop r22
pop r24
call _Z6DrawPxhhh
call _Z12DisplaySlatev
#Integer
ldi    r24,lo8(6)
ldi    r25,hi8(6)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
#Integer
ldi    r24,lo8(6)
ldi    r25,hi8(6)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
ldi    r22,2
push   r22
pop r20
pop r22
pop r24
call _Z6DrawPxhhh
call _Z12DisplaySlatev
#Integer
ldi    r24,lo8(2)
ldi    r25,hi8(2)
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
ldi    r22,5
push   r22
pop r20
pop r22
pop r24
call _Z6DrawPxhhh
call _Z12DisplaySlatev
jmp MJ_L5
MJ_L3:
#IFStatement
ldi r22, 0
push r22
pop    r24
ldi    r25, 0
cp     r24, r25
brne MJ_L10
jmp MJ_L9
MJ_L10:
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
ldi    r24,lo8(1)
ldi    r25,hi8(1)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
ldi    r22,5
push   r22
pop r20
pop r22
pop r24
call _Z6DrawPxhhh
call _Z12DisplaySlatev
#Integer
ldi    r24,lo8(6)
ldi    r25,hi8(6)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
#Integer
ldi    r24,lo8(1)
ldi    r25,hi8(1)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
ldi    r22,6
push   r22
pop r20
pop r22
pop r24
call _Z6DrawPxhhh
call _Z12DisplaySlatev
jmp MJ_L11
MJ_L9:
#Integer
ldi    r24,lo8(6)
ldi    r25,hi8(6)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
#Integer
ldi    r24,lo8(6)
ldi    r25,hi8(6)
push   r25
push   r24
#ByteCast
pop    r24
pop    r25
push   r24
ldi    r22,2
push   r22
pop r20
pop r22
pop r24
call _Z6DrawPxhhh
call _Z12DisplaySlatev
MJ_L11:
MJ_L5:
MJ_L2:


/* epilogue start */
    endLabel:
    jmp endLabel
    ret
    .size   main, .-main

