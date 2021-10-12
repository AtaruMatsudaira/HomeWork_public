public class Markov_Value_Parent {
  public int pitch;
  public ArrayList<Markov_Value> oddsList;
  public Markov_Value_Parent(int pitch, ArrayList<Markov_Value> oddsList) {
    this.pitch = pitch;
    this.oddsList = oddsList;
  }
  public Markov_Value_Parent(int pitch) {
    this.pitch = pitch;
    oddsList=new ArrayList<Markov_Value>();
  }
  public void setOddsValue(ArrayList<Markov_Value> oddsList) {
    this.oddsList=oddsList;
  }
}

public class Markov_Value {
  public int pitch;
  public float odds;
  public int velocity = 45;
  public Markov_Value(int pitch, float odds) {
    this.pitch = pitch;
    this.odds = odds;
  }
  public Markov_Value(int pitch, float odds,int velocity) {
    this.pitch = pitch;
    this.odds = odds;
    this.velocity = velocity;
  }
}

public class Note {
  public int pitch;
  public int velocity = 45;
  public Note() {
  }
  public Note(int pitch, int velocity) {
    this.pitch = pitch;
    this.velocity = velocity;
  }
}

public class SheetSet {
  public int numberNote;
  public ArrayList<Markov_Value_Parent> sheet;
  public SheetSet(int numberNote, ArrayList<Markov_Value_Parent> sheet) {
    this.numberNote = numberNote;
    this.sheet = sheet;
  }
}