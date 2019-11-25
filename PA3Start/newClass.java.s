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
ldi r25, hi8(61157)
ldi r24, lo8(61157)
push r25
push r24
#Integer
ldi    r24,lo8(1)
ldi    r25,hi8(1)
push   r25
push   r24
pop r22
pop r23
pop r24
pop r25
call _Z10Tone_Startjj
ldi r24, lo8(0)
ldi r25, hi8(0)
call malloc
push r25
push r24
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
pop r20
#notdoingexp
pop r22
#notdoingexp
pop r24
pop r25
call newerClass_tones
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
ldi r25, hi8(61157)
ldi r24, lo8(61157)
push r25
push r24
#Integer
ldi    r24,lo8(1)
ldi    r25,hi8(1)
push   r25
push   r24
pop r22
pop r23
pop r24
pop r25
call _Z10Tone_Startjj
ldi r24, lo8(0)
ldi r25, hi8(0)
call malloc
push r25
push r24
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
pop r20
#notdoingexp
pop r22
#notdoingexp
pop r24
pop r25
call newerClass_tones
ldi r24, lo8(0)
ldi r25, hi8(0)
call malloc
push r25
push r24
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
pop r20
#notdoingexp
pop r22
#notdoingexp
pop r24
pop r25
call newerClass_tones
ldi r24, lo8(0)
ldi r25, hi8(0)
call malloc
push r25
push r24
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
pop r20
#notdoingexp
pop r22
#notdoingexp
pop r24
pop r25
call newerClass_tones
ldi r24, lo8(0)
ldi r25, hi8(0)
call malloc
push r25
push r24
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
pop r20
#notdoingexp
pop r22
#notdoingexp
pop r24
pop r25
call newerClass_tones
ldi r25, hi8(61157)
ldi r24, lo8(61157)
push r25
push r24
#Integer
ldi    r24,lo8(1000000)
ldi    r25,hi8(1000000)
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
.global newerClass_tones
.type newerClass_tones, @function
newerClass_tones:
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
#IFStatement
ldd r24, Y + 3
push r24
ldd r24, Y + 4
push r24
pop    r18
pop    r24
cp    r24, r18
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
ldi    r24,lo8(7)
ldi    r25,hi8(7)
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
jmp MJ_L2
MJ_L0:
MJ_L2:
pop r30
pop r30
pop r30
pop r30
pop r28
pop r29
ret
.size newerClass_tones, .-newerClass_tones
