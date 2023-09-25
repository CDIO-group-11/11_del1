package App.spil;

//Fake ClassA (SKAL SLETTES SENERE!!!!)
class ClassA {
    int x = 3;
    int y = 3;
    int p =2;
}

class DelOpgave1 {
public static void main(String[] args) {
    // Call class med dice values og spiller points
    ClassA a = new ClassA();
    // Call dice values - placeholder er Die1 og Die2
    // Denne linje sætter Die1 lige med "a.x" Som er: "a" er det jeg kaldte ClassA
    // og x er den variable jeg vil have fra ClassA
    int Die1 = a.x;
    // Jeg gør det igen for at få Die2 med y istedet for x
    int Die2 = a.y;
    // MAYBE Call curent spiller tur

    // Call value for spiller point - placeholder er Player point
    // "a" er det jeg kaldte class, "p" er den værdi jeg vil have fra ClassA
    int PlayerPoint = a.p;

    if (Die1 == Die2);
    PlayerPoint = 0;


}


}
