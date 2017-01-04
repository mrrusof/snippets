% public static void main(String[] args) { ... }

md(MD) :-
    am(AM),
    os(OS),
    rt(RT),
    mn(MN),
    pl(PL),
    mb(RT, MB),
    P1i = [MN, '(', PL, ')'],
    P2i = [AM, OS, RT, MNPL, MB],
    md_aux(P1i, P2i, MNPL, MD).
md(MD) :-
    am(AM),
    ass(ASS),
    rt(RT),
    mn(MN),
    pl(PL),
    P1i = [MN, '(', PL, ')'],
    P2i = [AM, ASS, RT, MNPL],
    md_aux(P1i, P2i, MNPL, MD).

md_aux(P1i, P2i, MNPL, MD) :-
    exclude((==('')), P1i, P1o),
    atomic_list_concat(P1o, '', MNPL),
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

v(byte, 1).
v(short, 1).
v(int, 1).
v(long, 1).
v('String', '"hello"').
v('StringBuilder', 'new StringBuilder("hello")').

mn(task).

pl('').
pl('int n').

mb(void, '{ }').
mb(void, '{ return; }').
mb(RT, MB) :-
    v(RT, V),
    atomic_list_concat(['{ return ', V, '; }'], '', MB).


main :-
    prompt(_, ''),
    md(MD),
    print(MD),
    nl,
    fail.
main :-
    halt.
