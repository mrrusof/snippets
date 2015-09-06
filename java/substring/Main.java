public class Main {
    public static boolean issub(String sub, String text) {
	if(sub.length() == 0) return true;
	for(int i = 0; i<text.length(); i++) {
	    int j = 0;
	    while(i+j < text.length() && j < sub.length() && text.charAt(i+j) == sub.charAt(j)) j++;
	    if(j == sub.length()) return true;
	}
	return false;
    }

    public static void main(String[] args) {
	String[] subs = {"", " ", "the", "first", "motor", "fast", "tor."};
	String[] texts = {"the first motor", ""};
	for(String sub : subs)
	    for(String text : texts)
		System.out.printf("issub(\"%s\", \"%s\") = %b\n", sub, text, issub(sub, text));
    }
}
