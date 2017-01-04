md(MD) :-
    am(AM),
    ass(ASS),
    rt(RT),
    mn(MN),
    pl(PL),
    P1i = [MN, '(', PL, ')'],
    exclude((==('')), P1i, P1o),
    atomic_list_concat(P1o, '', MNPL),
    P2i = [AM, ASS, RT, MNPL],
    exclude((==('')), P2i, P2o),
    atomic_list_concat(P2o, ' ', MD).

am(public).
am(protected).
am(''). % package
am(private).

ass(abstract).
ass(ASS) :-
    os(OS),
    ( PARTS = [abstract, OS] | PARTS = [OS, abstract] ),
    atomic_list_concat(PARTS, ' ', ASS).

os(static).
os(final).
os('static final').
os('final static').

rt(void).
rt(T) :- t(T).

t(byte).
t(short).
t(int).
t(long).
t('String').
t('StringBuilder').

mn(task).

pl('').
pl('int n').

main :-
    prompt(_, ''),
    md(MD),
    print(MD),
    nl,
    fail.
main :-
    halt.
