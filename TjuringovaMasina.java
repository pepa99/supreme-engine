package tjuringova_masina;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TjuringovaMasina extends Frame implements ActionListener {
	private TextArea program=new TextArea();   //deklaracija svih promenljivih
	private TextField pocetnatraka=new TextField(); //polje za unos poc trake
	private TextField pocpozicija=new TextField();//polje za unos poc poz. glave
	private Label tacnost=new Label(); //labela koja ipisuje rezultat
	private int koraci=0;  //promenljiva koja broji broj izvrsenih koraka
	private Label tacnost1=new Label();
	private Label traka=new Label("Traka:"); //labela koja prikazuje traku
	private Choice izbor=new Choice();
	private Button namesti=new Button("Namesti");
	private String[] komande;
	private Button izvrsi=new Button("Izvrsi");
	private Menu meni=new Menu("Pomoc");
	private Dijalog dijalog=new Dijalog(this,"Pomoc");
	private Dijalog tacno=new Dijalog(this,"");
	private Button korak=new Button("Korak");
	private Label lstanje=new Label("Stanje: ");
	private Button ucitaj=new Button("Ucitaj");
	private String stanje="q0";
	private  String dod1="f(q0,b)=(q-,b,+1)\r\n" +//ucitavanje nekih vec 
			"f(q0,0)=(q1,0,+1)\r\n" +          //postojecih programa za T.M.
			"f(q0,1)=(q1,1,+1)\r\n" + 
			"f(q1,b)=(q2,b,-1)\r\n" + 
			"f(q1,0)=(q1,0,+1)\r\n" + 
			"f(q1,1)=(q1,1,+1)\r\n" + 
			"f(q2,b)=(q+,1,+1)\r\n" + 
			"f(q2,0)=(q+,1,+1)\r\n" + 
			"f(q2,1)=(q2,0,-1)";
	private String odz1="f(q0,b)=(q-,b,+1)\r\n" + 
			"f(q0,0)=(q1,0,+1)\r\n" + 
			"f(q0,1)=(q1,1,+1)\r\n" + 
			"f(q1,b)=(q2,b,-1)\r\n" + 
			"f(q1,0)=(q1,0,+1)\r\n" + 
			"f(q1,1)=(q1,1,+1)\r\n" + 
			"f(q2,b)=(q-,b,+1)\r\n" + 
			"f(q2,0)=(q3,1,-1)\r\n" + 
			"f(q2,1)=(q+,0,+1)\r\n" + 
			"f(q3,b)=(q4,b,+1)\r\n" + 
			"f(q3,0)=(q3,1,-1)\r\n" + 
			"f(q3,1)=(q+,0,+1)\r\n" + 
			"f(q4,b)=(q+,0,+1)\r\n" + 
			"f(q4,0)=(q-,0,+1)\r\n" + 
			"f(q4,1)=(q4,b,+1)";
	private String delj4="f(q0,b)=(q-,b,+1)\r\n" + 
			"f(q0,0)=(q1,0,+1)\r\n" + 
			"f(q0,1)=(q1,1,+1)\r\n" + 
			"f(q1,b)=(q2,b,-1)\r\n" + 
			"f(q1,0)=(q1,0,+1)\r\n" + 
			"f(q1,1)=(q1,1,+1)\r\n" + 
			"f(q2,b)=(q-,b,+1)\r\n" + 
			"f(q2,0)=(q3,0,-1)\r\n" + 
			"f(q2,1)=(q-,1,+1)\r\n" + 
			"f(q3,b)=(q-,b,+1)\r\n" + 
			"f(q3,0)=(q+,0,+1)\r\n" + 
			"f(q3,1)=(q-,1,+1)";
	private String delj8="f(q0,b)=(q-,b,+1)\r\n" + 
			"f(q0,0)=(q1,0,+1)\r\n" + 
			"f(q0,1)=(q1,1,+1)\r\n" + 
			"f(q1,b)=(q2,b,-1)\r\n" + 
			"f(q1,0)=(q1,0,+1)\r\n" + 
			"f(q1,1)=(q1,1,+1)\r\n" + 
			"f(q2,b)=(q-,b,+1)\r\n" + 
			"f(q2,0)=(q3,0,-1)\r\n" + 
			"f(q2,1)=(q-,1,+1)\r\n" + 
			"f(q3,b)=(q-,b,+1)\r\n" + 
			"f(q3,0)=(q4,0,-1)\r\n" + 
			"f(q3,1)=(q-,1,+1)\r\n" + 
			"f(q4,b)=(q-,b,+1)\r\n" + 
			"f(q4,0)=(q+,0,+1)\r\n" + 
			"f(q4,1)=(q-,1,+1)";
	private String kompl="f(q0,b)=(q-,b,+1)\r\n" + 
			"f(q0,0)=(q1,1,+1)\r\n" + 
			"f(q0,1)=(q1,0,+1)\r\n" + 
			"f(q1,b)=(q+,b,-1)\r\n" + 
			"f(q1,0)=(q1,1,+1)\r\n" + 
			"f(q1,1)=(q1,0,+1)";
	private String pal="f(q0,b)=(q-,b,+1)\r\n" + 
			"f(q0,0)=(q1,b,+1)\r\n" + 
			"f(q0,1)=(q2,b,+1)\r\n" + 
			"f(q1,b)=(q3,b,-1)\r\n" + 
			"f(q1,0)=(q1,0,+1)\r\n" + 
			"f(q1,1)=(q1,1,+1)\r\n" + 
			"f(q2,b)=(q4,b,-1)\r\n" + 
			"f(q2,0)=(q2,0,+1)\r\n" + 
			"f(q2,1)=(q2,1,+1)\r\n" + 
			"f(q3,b)=(q+,b,+1)\r\n" + 
			"f(q3,0)=(q5,b,-1)\r\n" + 
			"f(q3,1)=(q-,1,+1)\r\n" + 
			"f(q4,b)=(q+,b,+1)\r\n" + 
			"f(q4,0)=(q-,b,-1)\r\n" + 
			"f(q4,1)=(q5,b,-1)\r\n" + 
			"f(q5,b)=(q6,b,+1)\r\n" + 
			"f(q5,0)=(q5,0,-1)\r\n" +  
			"f(q5,1)=(q5,1,-1)\r\n" + 
			"f(q6,b)=(q+,b,+1)\r\n" + 
			"f(q6,0)=(q1,b,+1)\r\n" + 
			"f(q6,1)=(q2,b,+1)";
	private String pomoc="Korisnik treba da izabere jedan od programa T.M."
	   + "iz liste i da ga  Ucita,\n"
	   + "ili da ga rucno upise u polje za program.\n"
	   +"Kada je korisnik namestio pocetnu traku i poziciju glave \ni pripremio"
	   + " je pritiskanjem dugmeta namesti,\n"
	   +"treba da izabere jednu od sledecih opcija:\n"+"\n"
	   +"Korak, za rucno prelazenje sa jednog koraka na sledeci."+"\n"
	   +"Izvrsi koje ce izvrsiti ceo program bez prikazivanja koraka.";
	public TjuringovaMasina() {              //konstruktor za kreiranje
		super("Tjuringova Masina");          //prozora
		dodajKomponente();                    
		setSize(550,500);
		setBackground(Color.cyan);
		addWindowListener(new WindowAdapter ()
		{public void windowClosing(WindowEvent e) {dispose();}});
		setLocation(500, 200);
		setVisible(true);
		setResizable(false);
	}
	class Dijalog extends Dialog{//klasa preko koje se prikazuju prozori za pomoc
		Dijalog(Frame roditelj,String s){// i za rezulata na kraju
			super(roditelj,s,true);
			setLocation(700,250);
			setResizable(false);
			setSize(100,95);
			addWindowListener(new WindowAdapter () 
			{public void windowClosing(WindowEvent e) {setVisible(false);}});
		}
	}
	public void dodajKomponente() {        //dodavanje svih komponenti u prozor
		Label l1=new Label("Pocetna traka i pozicija glave:");//i podesvanja njihovih
		l1.setFont(new Font(null,Font.BOLD,16)); //velicina i pozicija
		MenuBar bar=new MenuBar();
		meni.add("Pomoc");
		bar.add(meni);
		setMenuBar(bar);
		meni.addActionListener(this);
		Panel p1=new Panel();
		pocetnatraka.setMinimumSize(new Dimension(200,25));
		pocetnatraka.setPreferredSize(new Dimension(200,25));
		pocetnatraka.setMaximumSize(new Dimension(200,25));
		pocetnatraka.setFont(new Font(null,Font.BOLD,18));
		pocpozicija.setMinimumSize(new Dimension(30,25));
		pocpozicija.setPreferredSize(new Dimension(30,25));
		pocpozicija.setMaximumSize(new Dimension(30,25));
		pocpozicija.setFont(new Font(null,Font.BOLD,18));
		p1.add(l1); p1.add(pocetnatraka); p1.add(pocpozicija);
		Panel p2=new Panel();
		Label pr=new Label("Program:");
		pr.setFont(new Font(null,Font.BOLD,20));
		p2.add(pr);
	    Panel p3=new Panel();
	    traka.setFont(new Font(null,Font.BOLD,22));
	    lstanje.setFont(new Font(null,Font.BOLD,22));
	    izbor.setFont(new Font(null,Font.BOLD,16));
	    izbor.setBackground(Color.YELLOW);
	    izbor.addItem("Dodaj 1");
	    izbor.addItem("Oduzmi 1");
	    izbor.add("Deljivost sa 4");
	    izbor.addItem("Deljivost sa 8");
	    izbor.addItem("Komplement");
	    izbor.addItem("Palindrom");
	    p2.add(izbor);
	    ucitaj.setFont(new Font(null,Font.BOLD,16));
	    p2.add(ucitaj);
	    ucitaj.addActionListener(this);
	    program.setFont(new Font(null,Font.BOLD,14));
	    p2.add(program);
	    p2.add(traka);
	    Panel p=new Panel();
	    namesti.addActionListener(this);
	    namesti.setFont(new Font(null,Font.BOLD,16));
	    korak.addActionListener(this);
	    korak.setFont(new Font(null,Font.BOLD,16));
	    tacnost.setFont(new Font(null,Font.BOLD,22));
	    tacnost1.setFont(new Font(null,Font.BOLD,22));
	    izvrsi.addActionListener(this);
	    izvrsi.setFont(new Font(null,Font.BOLD,16));
	    izvrsi.setEnabled(false);
	    korak.setEnabled(false);
	    p3.add(lstanje);
	    p3.add(korak);
	    p3.add(namesti);
	    p3.add(izvrsi);
	    p3.add(tacnost);
	    add(p1,"North"); add(p2); add(p3,"South");
	    p.add(tacnost1);
	    tacno.setSize(300, 200);
	    tacno.add(p);
	    TextArea pom=new TextArea();
	    pom.setFont(new Font(null, Font.BOLD,16));
	    pom.setText(pomoc);
	    pom.setEnabled(false);
	    pom.setBackground(Color.RED);
	    dijalog.setSize(700, 230);
	    dijalog.setModal(false);
	    dijalog.add(pom);
	}
     private static class Elem{       //kreiranje dvostruko ulancane
    	 char c;                      //liste koja simulira traku T.M.
    	 Elem prethodni;
    	 Elem sledeci;
    	 Elem(char ch){c=ch; prethodni=null; sledeci=null;}
     }
     private Elem prvi, posl, tek;//prvi, poslednji i tekuci element trake
     public void dodajnakraj(char i){//funkcija za dodavanje elemenata 
    	 if(prvi==null) {prvi=new Elem(i); posl=prvi;}//na kraj liste
    	 else
    	 {posl.sledeci=new Elem(i);
    	 posl.sledeci.prethodni=posl;
    	 posl=posl.sledeci;}
     }
     public void dodajnapocetak(char c) {//funkcija za dodavanje na pocetak liste
    	 Elem novi=new Elem(c);
    	 novi.sledeci=prvi;
    	 if(prvi!=null)
    	 prvi.prethodni=novi;
    	 prvi=novi;
    	 if(posl==null) posl=prvi;
     }
     public String toString() {  //funkcija za ispis polja trake
    	 Elem tek=prvi;
    	 String s="";
    	 while(tek!=null) {
    		 if(tek==this.tek && !stanje.equals("q+") && !stanje.equals("q-")) 
    			 s+="["+tek.c+"]";
    		 else
    		 s+=tek.c+" ";
    		 tek=tek.sledeci;
    	 }
    	 return s;
     }
     public Elem dohvati(int i) { //funkcija koja dohvata
    	 Elem tekuci=prvi;        //i-ti element na traci
    	 int j=1;
    	 while(tekuci!=null && j<i) {
    		 tekuci=tekuci.sledeci;
    		 j++;
    	 }
    	 return tekuci;
     }
     public void napred() {//u slucaju da se doslo do kraja liste, dodavanje
    	 if(tek==null) {     //novog elementa
    		 dodajnakraj('b'); //posto je traka T.M. beskonacna
    		 tek=posl;
    	 } else
    	 tek=tek.sledeci;
    }
     public void nazad() {  //u slucaju da se doslo do pocetka liste
    	 if(tek==null) {    //dodavanje novog elementa posto je
    		 dodajnapocetak('b'); //traka beskonacna
    		 tek=prvi;
    	 } else
    	 tek=tek.prethodni;
    }
     public void isprazni() { //oslobadjanje trake i
    	 prvi=posl=null;      //omogucavanje za unos nove pocetne trake
     }
     public static void main(String[] args) {
    	 TjuringovaMasina masina=new TjuringovaMasina();
     }
     private void dodajListu(String s) {   //funkcija koja ucitava pocetnu traku
    	 for(int i=0;i<s.length();i++)     //iz polja za unos
    		 dodajnakraj(s.charAt(i));
     }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==namesti) {  //obrada dogadjaja pruzrokovanog
			isprazni();               //klikom na dugme namesti
			koraci=0;
			stanje="q0";
			tacnost.setText(""); 
			dodajListu(pocetnatraka.getText());
			tek=dohvati(Integer.parseInt(pocpozicija.getText()));
			traka.setText(toString());
			komande=program.getText().split("\\n");
			korak.setEnabled(true);
			izvrsi.setEnabled(true);
			revalidate();
		}
		if(e.getSource()==izvrsi) { //obrada dogadjaja prouzrokovanog
		  int koraci=0;           //klikom na dugme izvrsi
		  while(!stanje.equals("q+") && !stanje.equals("q-")) {
		  for(int i=0;i<komande.length;i++) {
		  if(komande[i].charAt(3)==stanje.charAt(1) && komande[i].charAt(5)==tek.c){
					if(tek!=null) tek.c=komande[i].charAt(12);
					if(komande[i].charAt(14)=='+') {
						napred();
						if(tek==null) napred();
					}
					if(komande[i].charAt(14)=='-') {
						nazad();
						if(tek==null) nazad();
					}
					koraci++;
					stanje="q"+komande[i].charAt(10);
					break;
				}
			}
			traka.setText(toString());
			lstanje.setText("Stanje: "+stanje);
			if(stanje.equals("q+") || stanje.equals("q-")) {
				korak.setEnabled(false);
				izvrsi.setEnabled(false);
				ucitaj.setEnabled(true);
				if(stanje.equals("q+"))  {
					tacnost1.setText("Tacno, broj koraka: "+koraci);
				tacnost.setText("Tacno");}
				else {tacnost.setText("Netacno");
				tacnost1.setText("Netacno, broj koraka: "+koraci);}
				tacno.setVisible(true);
			}
			revalidate();
			}
		}
		if(e.getSource()==korak) { //obrada dogadjaja prouzrokovanog klikom na dugme korak
			for(int i=0;i<komande.length;i++) {
			if(komande[i].charAt(3)==stanje.charAt(1) && komande[i].charAt(5)==tek.c){
					if(tek!=null) tek.c=komande[i].charAt(12);
					if(komande[i].charAt(14)=='+') {
						napred();
						if(tek==null) napred();
					}
					if(komande[i].charAt(14)=='-') {
						nazad();
						if(tek==null) nazad();
					}
					koraci++;
					stanje="q"+komande[i].charAt(10);
					break;
				}
			}
			traka.setText(toString());
			lstanje.setText("Stanje: "+stanje);
			if(stanje.equals("q+") || stanje.equals("q-")) {
				korak.setEnabled(false);
				izvrsi.setEnabled(false);
				ucitaj.setEnabled(true);
				if(stanje.equals("q+")) {tacnost.setText("Tacno");
				tacnost1.setText("Tacno, broj koraka: "+koraci);}
				else {tacnost.setText("Netacno");
				tacnost1.setText("Netacno, broj koraka: "+koraci);}
				tacno.setVisible(true);
			}
			revalidate();
		}                             
	   if(e.getSource()==ucitaj){//ucitavanje postojeceg programa T.M. u polje za program
		   if(izbor.getSelectedItem().contentEquals("Dodaj 1")){//preko dugmeta Ucitaj
			   program.setText(dod1);
		   }
		   if(izbor.getSelectedItem().equals("Oduzmi 1")) {
			   program.setText(odz1);
		   }
		   if(izbor.getSelectedItem()=="Deljivost sa 4") {
			   program.setText(delj4);
		   }
		   if(izbor.getSelectedItem()=="Deljivost sa 8") {
			   program.setText(delj8);
		   }
		   if(izbor.getSelectedItem()=="Komplement") {
			   program.setText(kompl);
		   }
		   if(izbor.getSelectedItem()=="Palindrom") {
			   program.setText(pal);
		   }
	   }
	   if(e.getActionCommand().equals("Pomoc")) {
		   dijalog.setVisible(true);  //prikazivanje prozora Pomoc
	   }
	}
}
