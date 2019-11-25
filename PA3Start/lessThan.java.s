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
pop r20
#notdoingexp
pop r22
#notdoingexp
pop r24
pop r25
call do_bluedot
#IFStatement
#Integer
ldi    r24,lo8(1)
ldi    r25,hi8(1)
push   r25
push   r24
#Integer
ldi    r24,lo8(2)
ldi    r25,hi8(2)
push   r25
push   r24
pop    r18
pop    r19
pop    r24
pop    r25
cp    r24, r18
cpc   r25, r19
brlt MJ_L7
MJ_L6:
ldi     r24, 0
jmp      MJ_L8
MJ_L7:
ldi    r24, 1
MJ_L8:
push   r24
pop    r24
ldi    r25, 0
cp     r24, r25
brne MJ_L1
jmp MJ_L0
MJ_L1:
ldi r25, hi8(61157)
ldi r24, lo8(61157)
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
jmp MJ_L2
MJ_L0:
MJ_L2:
#IFStatement
#Integer
ldi    r24,lo8(2)
ldi    r25,hi8(2)
push   r25
push   r24
#Integer
ldi    r24,lo8(1)
ldi    r25,hi8(1)
push   r25
push   r24
pop    r18
pop    r19
pop    r24
pop    r25
cp    r24, r18
cpc   r25, r19
brlt MJ_L18
MJ_L17:
ldi     r24, 0
jmp      MJ_L19
MJ_L18:
ldi    r24, 1
MJ_L19:
push   r24
pop    r24
ldi    r25, 0
cp     r24, r25
brne MJ_L12
jmp MJ_L11
MJ_L12:
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
ldi    r22,1
push   r22
pop r20
pop r22
pop r24
call _Z6DrawPxhhh
call _Z12DisplaySlatev
ldi r25, hi8(57724)
ldi r24, lo8(57724)
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
ldi r25, hi8(48541)
ldi r24, lo8(48541)
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
jmp MJ_L13
MJ_L11:
MJ_L13:
ldi r25, hi8(32397)
ldi r24, lo8(32397)
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
ldi r25, hi8(61157)
ldi r24, lo8(61157)
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
ldi r25, hi8(32397)
ldi r24, lo8(32397)
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
ldi r25, hi8(61157)
ldi r24, lo8(61157)
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
ldi r25, hi8(32397)
ldi r24, lo8(32397)
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
ldi r25, hi8(61157)
ldi r24, lo8(61157)
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

   .text
.global do_bluedot
.type do_bluedot, @function
do_bluedot:
push r29
push r28
ldi r30, 0
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
ldd r24, Y + 3
push r24
ldd r24, Y + 4
push r24
ldi    r22,1
push   r22
pop r20
pop r22
pop r24
call _Z6DrawPxhhh
call _Z12DisplaySlatev
pop r30
pop r30
pop r30
pop r30
pop r28
pop r29
ret
.size do_bluedot, .-do_bluedot
