import java.util.*;
import javax.sound.midi.*;
import java.io.*;
import themidibus.*;

ArrayList<Markov_Value_Parent> markovSheets_A, markovSheets_B, frogSong_A, frogSong_B, zigzag;
final int C3 = 60, D3 = 62, E3 = 64, F3 = 65, G3 = 67, A4 =  69, B4 = 71;
void setup() {

  ArrayList<SheetSet> sheetNoteList = new ArrayList<SheetSet>(); 

  size(200, 200);
  background(0);
  noLoop();

  insertMusic();

  //分割した要素をそれぞれ配置
  sheetNoteList.add(new SheetSet(16, markovSheets_A));
  sheetNoteList.add(new SheetSet(16, markovSheets_B));
  sheetNoteList.add(new SheetSet(16, markovSheets_A));
  sheetNoteList.add(new SheetSet(16, frogSong_A));
  sheetNoteList.add(new SheetSet(12, markovSheets_B));
  sheetNoteList.add(new SheetSet(32, markovSheets_A));

  MarkovGenerate baseStar = new MarkovGenerate(sheetNoteList, "baseStar");
  baseStar = null;
  sheetNoteList.clear();

  sheetNoteList.add(new SheetSet(16, markovSheets_A));
  sheetNoteList.add(new SheetSet(16, markovSheets_B));
  sheetNoteList.add(new SheetSet(16, markovSheets_A));
  sheetNoteList.add(new SheetSet(16, frogSong_A));
  sheetNoteList.add(new SheetSet(12, markovSheets_B));
  sheetNoteList.add(new SheetSet(32, markovSheets_A));

  MarkovGenerate starInFrog = new MarkovGenerate(sheetNoteList, "starInFrog");
  starInFrog = null;
  sheetNoteList.clear();

  for (int i = 0; i <8; i++) {
    sheetNoteList.add(new SheetSet(8, frogSong_A));
    sheetNoteList.add(new SheetSet(8, frogSong_B));
  }
  MarkovGenerate zigzagFrog = new MarkovGenerate(sheetNoteList, "zigzagFrog");
  sheetNoteList.clear();
  zigzagFrog = null;

  sheetNoteList.add(new SheetSet(80, zigzag));

  MarkovGenerate zigzag = new MarkovGenerate(sheetNoteList, "zigzag");
  zigzag = null;
  println("process finished");
  exit();
}

//曲データの追加 (本来であればここをmidi取り込みでやりたかったです...)
void insertMusic()
{
  markovSheets_A = new ArrayList<Markov_Value_Parent>();
  markovSheets_A.add(
    new Markov_Value_Parent(C3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(C3, 0.5), 
    new Markov_Value(G3, 0.5)
    )
    )
    )
    );
  markovSheets_A.add(
    new Markov_Value_Parent(D3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(C3, 0.5), 
    new Markov_Value(D3, 0.5)
    )
    )
    )
    );
  markovSheets_A.add(
    new Markov_Value_Parent(E3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(D3, 0.5), 
    new Markov_Value(E3, 0.5)
    )
    )
    )
    );
  markovSheets_A.add(
    new Markov_Value_Parent(F3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(E3, 0.5), 
    new Markov_Value(F3, 0.5)
    )
    )
    )
    );
  markovSheets_A.add(
    new Markov_Value_Parent(G3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(F3, 0.3333), 
    new Markov_Value(F3, 0.3333), 
    new Markov_Value(A4, 0.3333)
    )
    )
    )
    );
  markovSheets_A.add(
    new Markov_Value_Parent(A4, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(G3, 0.5), 
    new Markov_Value(A4, 0.5)
    )
    )
    )
    );

  markovSheets_B = new ArrayList<Markov_Value_Parent>();
  markovSheets_B.add(
    new Markov_Value_Parent(C3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(G3, 1)
    )
    )
    )
    );
  markovSheets_B.add(
    new Markov_Value_Parent(D3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(G3, 1)
    )
    )
    )
    );
  markovSheets_B.add(
    new Markov_Value_Parent(E3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(D3, 0.4), 
    new Markov_Value(E3, 0.4), 
    new Markov_Value(G3, 0.2)
    )
    )
    )
    );
  markovSheets_B.add(
    new Markov_Value_Parent(F3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(E3, 0.5), 
    new Markov_Value(F3, 0.5)
    )
    )
    )
    );
  markovSheets_B.add(
    new Markov_Value_Parent(G3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(F3, 0.5), 
    new Markov_Value(G3, 0.5)
    )
    )
    )
    );
  frogSong_A = new ArrayList<Markov_Value_Parent>();
  frogSong_A.add(
    new Markov_Value_Parent(C3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(D3, 0.5), 
    new Markov_Value(E3, 0.5)
    )
    )
    )
    );
  frogSong_A.add(
    new Markov_Value_Parent(D3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(C3, 0.5,90), 
    new Markov_Value(E3, 0.5)
    )
    )
    )
    );
  frogSong_A.add(
    new Markov_Value_Parent(E3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(D3, 0.5), 
    new Markov_Value(F3, 0.5)
    )
    )
    )
    );
  frogSong_A.add(
    new Markov_Value_Parent(F3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(E3, 0.5,90), 
    new Markov_Value(G3, 0.5)
    )
    )
    )
    );
  frogSong_A.add(
    new Markov_Value_Parent(G3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(F3, 0.66667), 
    new Markov_Value(A4, 0.33333)
    )
    )
    )
    );
  frogSong_A.add(
    new Markov_Value_Parent(A4, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(G3, 1)
    )
    )
    )
    );

  frogSong_B = new ArrayList<Markov_Value_Parent>();

  frogSong_B.add(
    new Markov_Value_Parent(C3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(C3, 0.5,45/2), 
    new Markov_Value(D3, 0.5,45/2)
    )
    )
    )
    );
  frogSong_B.add(
    new Markov_Value_Parent(D3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(C3, 0.3333,90), 
    new Markov_Value(D3, 0.3333,45/2), 
    new Markov_Value(E3, 0.3333,45/2)
    )
    )
    )
    );
  frogSong_B.add(
    new Markov_Value_Parent(E3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(D3, 0.3333), 
    new Markov_Value(E3, 0.3333,45/2), 
    new Markov_Value(F3, 0.3333,45/2)
    )
    )
    )
    );
  frogSong_B.add(
    new Markov_Value_Parent(F3, 
    new ArrayList<Markov_Value>(
    Arrays.asList( 
    new Markov_Value(E3, 0.5), 
    new Markov_Value(F3, 0.5,45/2)
    )
    )
    )
    );
  zigzag = new ArrayList<Markov_Value_Parent>();
  zigzag.add(
    new Markov_Value_Parent(E3, 
    new ArrayList<Markov_Value>(
    Arrays.asList(
    new Markov_Value(C3, 1)
    )
    )
    )
    );
  zigzag.add(
    new Markov_Value_Parent(C3, 
    new ArrayList<Markov_Value>(
    Arrays.asList(
    new Markov_Value(E3, 1)
    )
    )
    )
    );
}
