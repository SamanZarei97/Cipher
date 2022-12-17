/**
 * Name : Saman Zarei
 * This program is about the privacy of our chats with the other people so
 * that no one understands what we are texting to each other by changing each
 * word to some secure letters(encoding) and after that change it back to the
 * original text(decoding).It means that in this program we will encode words
 * by some secure letters and after that decode them to the original text.
 */

/**
 * This class has designed to encode the words by secure letters
 * and after that change them back to the original text by decoding the
 * secure letters.
 * @author Saman Zarei
 */
public class Cipher{

  // Number of small letter 'a' letter in ASCII table.
  private final static int LOW_NUM = 97;
  // Number of small letter 'z' letter in ASCII table.
  private final static int HIGH_NUM = 122;
  // The number of english alphabet.
  private final static int ALPHABET_NUM = 26;
  // The empty string.
  private final static String EMPTY = "";

  /**
   * The letter is valid if it's small letter and this method has designed to
   * check letter. small letter is valid and ture but capital letter is not.
   * @param letter letter that needs to test to see if it's valid or not.
   * @return returns ture if letter is valid and false it's not.
   */
  public static boolean isLowerCase(char letter){
    // Checking to see if the letter is valid based on thier number at ASCII.
    if(letter < LOW_NUM || letter > HIGH_NUM  ){
      return false;
    }

    else{
      return true;
    }
  }

  /**
   * The word is valid if all of its letters are small and this method has
   * designed to check word. If all letters are not small or it's null, then
   * it's not valid.
   * @param str the word that needs to test to see if it's valid or not.
   * @return returns true if the word is valid and false if it's not.
   */
  public static boolean isLowerCase(String str){
    // At first step, str cannot be null.
    if(str != null){
      // Checking each letter of the str.
      for(int i = 0; i < str.length(); i++){
        // str is valid if all of its letters are small.
        if(isLowerCase(str.charAt(i))){
          continue;
        }

        else{
          return false;
        }
      }
    }

    else{
      return false;
    }

    return true;
  }

  /**
   * This method has designed to encode a letter by a secure letter for
   * its privacy.
   * @param plaintext original letter that needs to encode.
   * @param key a letter we used it to encode the plaintext.
   * @return returns the encoded letter.
   */
  public static char caesarShiftEncode(char plaintext, char key){

    // Encoded letter.
    char encodingCH;
    // Helper number to get number of encoded letter based on ASCII table
    int counter;

    //If plaintext or key are invalid, don't encode and equal it to plaintext
    if((isLowerCase(plaintext) != true) || (isLowerCase(key) != true)){
      encodingCH = plaintext;
    }

    else{
      // Calculating the counter.
      counter = ( (plaintext - LOW_NUM) + (key - LOW_NUM) ) % ALPHABET_NUM;
      // Get encoded letter by converting sum of counter and LOW_NUM to char
      encodingCH = (char)(counter + LOW_NUM);
    }

    return encodingCH;
  }

  /**
   * This method has designed to convert the secure letter to the original
   * by decoding the secure letter.
   * @param ciphertext secure letter that needs to decode.
   * @param key a letter we use it to decode the ciphertext.
   * @return returns the decoded letter.
   */
  public static char caesarShiftDecode(char ciphertext, char key){
    // Decoded letter.
    char decodingCH;
    // Helper number to get number of encoded letter based on ASCII table
    int counter;
    //If ciphertext or key are invalid don't decode and equal it to ciphertext
    if((isLowerCase(ciphertext) != true) || (isLowerCase(key) != true)){
      decodingCH = ciphertext;
    }

    else{
      // Calculating the counter.
      counter = ( (ciphertext - LOW_NUM) - (key - LOW_NUM)) % ALPHABET_NUM;
      // If counter is a negative number.
      if(counter < 0){
        // get decoded letter by converting sum of these 3 numbers to char
        decodingCH = (char)(counter + ALPHABET_NUM + LOW_NUM);
      }

      else{
        // get decoded letter by coverting sum of counter and LOW_NUM to char
        decodingCH = (char)(counter + LOW_NUM);
      }
    }

    return decodingCH;
  }

  /**
   * This method has designed to encode a word by a secure word for
   * its privacy.
   * @param plaintext a word that needs to be encoded.
   * @param key a word that we use it to encode plaintext.
   * @return returns the encoded word.
   */
  public static String vigenereEncode(String plaintext, String key){
    // helper word to arrenge each letter of key based on plaintext length.
    String str = EMPTY;
    // Eccoded word
    String encodingTX = EMPTY;
    // If plaintext is not valid, encoded word is null.
    if( (isLowerCase(plaintext) ) != true) {
      encodingTX = null;
    }
    // If key is not valid or it's empty, encoded word is null.
    else if( ( (isLowerCase(key) ) != true) || (key.equals(EMPTY) ) ){
      encodingTX = null;
    }

    else{
      // Toward the loop and cheking each letter of plaintext to match them.
      for(int i= 0; i < plaintext.length(); i++){
        // Arrenging each letter of key to match them with plaintext letters
        str = str + key.charAt(i % key.length());
        // Get encoded words by matching each letter of plaintext with str
        encodingTX += caesarShiftEncode(plaintext.charAt(i), str.charAt(i));
      }
    }

    return encodingTX;
  }

  /**
   * This method has designed to convert the secure word to the original word
   * by decoding the secure word.
   * @param ciphertext the secure word that needs to decode
   * @param key the word we use it to decode the ciphertext.
   * @return returns the decoded word.
   */
  public static String vigenereDecode(String ciphertext, String key){

    // Helper word to arrenge each letter of key based on ciphertext length
    String str = EMPTY;
    // Decoded word.
    String decodingTX = EMPTY;
    // If ciphertext is not valid, decoded word is null
    if( (isLowerCase(ciphertext) ) != true){
      decodingTX = null;
    }
    // If key is not valid or it's empty, decoded word is null
    else if( ( (isLowerCase(key) ) != true) || (key.equals(EMPTY) ) ){
      decodingTX = null;
    }

    else{
      // Toward the loop and cheking each letter of ciphertext to match them.
      for(int i = 0; i < ciphertext.length(); i++){
        // Arrenging each letter of key to match them with ciphertext letters
        str = str + key.charAt(i % key.length());
        // Get decoded words by matching each letter of ciphertext with str
        decodingTX += caesarShiftDecode(ciphertext.charAt(i),str.charAt(i));
      }
    }

    return decodingTX;
  }
}
