class MarkovGenerate {

  MidiBus mBus; 

  Sequence seq;
  int numberOfNotes=0;
  Note[] markovSeries; // C major scale

  ArrayList<SheetSet> sheetNoteList;
  String fName;

  public MarkovGenerate(ArrayList<SheetSet> sheetNoteList, String fileName) {
    this.sheetNoteList = sheetNoteList;
    this.fName = fileName;

    MidiBus.list();
    mBus = new MidiBus(this, -1, "Microsoft GS Wavetable Synth");
    //mBus = new MidiBus(this, - 1, "Bus 1");

    for (SheetSet sheet : sheetNoteList) {
      numberOfNotes += sheet.numberNote;
    }
    markovSeries = new Note[numberOfNotes]; // C major scale
    for (int i = 0; i < numberOfNotes; i++) {
      markovSeries[i]=new Note();
    }
    draw();
  }
  
  void draw() {
    String fileName = "C:\\MidiExport\\"+fName+".mid";
    int fileFormat = 1; // Standard MIDI file format: Type 1                
    int resolution = 480;
    int MM = 120;
    int trackNum = 1;
    int amp = 96;
    float nextStart = 0.0;
    float dur = 0.5;

    //tempo is microseconds per quarter note, or (60/MM)*10^6.             
    int tempo = (int)(60000000.0 / MM);
    byte lB = (byte)(tempo % Math.pow(16, 2));
    byte mB = (byte)((tempo % Math.pow(16, 4)) / Math.pow(16, 2));
    byte fB = (byte)((tempo % Math.pow(16, 6)) / Math.pow(16, 4));
    byte[] tempoData = {fB, mB, lB};

    //Create a sequence
    try {
      seq = new Sequence(Sequence.PPQ, resolution);
    } 
    catch(InvalidMidiDataException e) {  
      e.printStackTrace();
    }
    //Write atrack in the sequence
    Track tr = seq.createTrack();

    //Set a tempo
    MetaMessage tempoMssg;
    MidiEvent tempoEvent;
    try {
      tempoMssg = new MetaMessage();
      tempoMssg.setMessage(0x51, tempoData, tempoData.length);
      tempoEvent = new MidiEvent(tempoMssg, 0);
      tr.add(tempoEvent);
    }
    catch(InvalidMidiDataException e) {
      e.printStackTrace();
    }

    //Create a Markov series of notes
    int channel = 0;
    int velocity = 96;
    int noOfNotes = 24;
    Note[] notes = markov(noOfNotes);



    for (int i = 0; i < markovSeries.length; i++) {

      //Send note_on and note_off messages to the internal synth.
      print(notes[i].pitch + " "); 
      mBus.sendNoteOn(channel, notes[i].pitch, notes[i].velocity); 
      delay(200);
      mBus.sendNoteOff(channel, notes[i].pitch, notes[i].velocity); 
      delay(100);

      //Note On message                                              
      ShortMessage onMssg = new ShortMessage();
      try {
        onMssg.setMessage(ShortMessage.NOTE_ON, trackNum, notes[i].pitch, amp);
      }
      catch(InvalidMidiDataException e) {
        e.printStackTrace();
      }

      //startTime: thetime of the beginning of a note
      long startTime = (long)(nextStart * (resolution * (MM / 60.0)));
      MidiEvent noteOnEvent = new MidiEvent(onMssg, startTime);
      tr.add(noteOnEvent);

      //println("starTime: " + startTime);


      //endTime: the time of the ending of a note
      long endTime = startTime + (long)(dur * (resolution * (MM / 60.0)));
      //ticksPerSecond= resolution * (currentTempoInBeatsPerMinute / 60.0);                     

      //println("endTime: " + endTime);


      //Note off message using running status                        
      ShortMessage offMssg = new ShortMessage();
      try {
        offMssg.setMessage(ShortMessage.NOTE_OFF, trackNum, notes[i].pitch, 0);
      }
      catch(InvalidMidiDataException e) {
        e.printStackTrace();
      }
      //velocity = 0                                              
      MidiEvent noteOffEvent = new MidiEvent(offMssg, endTime);
      tr.add(noteOffEvent);

      nextStart += dur;
    }
    // Writeout a MIDI file onto the disk. 
    try {
      MidiSystem.write(seq, fileFormat, new FileOutputStream(fileName));
    }
    catch(IOException e) {
      e.printStackTrace();
    }
  }

  Note[] markov(int numberOfNotes) {

    int noteCount = 0;
    int pastLastIndex = 0;
    for (SheetSet sheet : sheetNoteList) {
      int nextPitch = 60; 
      int velocity = 45;
      for (int start = pastLastIndex; start < sheet.numberNote+pastLastIndex; start++) {
        markovSeries[start].pitch = nextPitch;
        markovSeries[start].velocity = velocity;
        int lastPitch = nextPitch; 
        float r = random(0.0, 1.0);
        float sumOdds = 0.0;
        for (Markov_Value_Parent value : sheet.sheet) {
          if (lastPitch==value.pitch) {
            for (Markov_Value noteValue : value.oddsList) {
              sumOdds += noteValue.odds;
              if (r<sumOdds) {
                nextPitch=noteValue.pitch;
                velocity = noteValue.velocity;
                println(nextPitch+" , "+velocity);
                break;
              }
            }
          }
        }
        noteCount++;
      }
      pastLastIndex=noteCount;
    }

    return markovSeries;
  }
}
