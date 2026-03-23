/**********************************************************************************
 * API: APIFileCipher - Suppose que la clé blowfish a déjà été généré
 * Description:	Utilise ApiBlowfish pour le chiffrement et déchiffrement de fichier
 * Auteur:		Didier Samfat
 * Date:		28 Mar 2021
 * Version:		1.0
 *********************************************************************************/

package myBlowfish;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Base64;


public class ApiFileCipher {

/**
 * Méthode qui lit un fichier est retourne ce qu'il a lu	
 * @param nomFichier: le fichier à lire
 * @return : la chaîne lue
 * @throws IOException
 */
	
static String read(String nomFichier) throws IOException {
        return new String(Files.readAllBytes(Paths.get(nomFichier)));
}

/**
 * Méthode qui chiffre un fichier donné avec une clé blowfish
 * Créer asussi un nouveau fichier au format nomFichier.cryp 
 * @param nomFichier : fichier qui doit être chiffré
 * @param clef : doit être généré au prálable
 * @return : le texte chifré encode en Base64
 * @throws Exception
 */
	
static String encrypt(String nomFichier, Key clef) throws Exception{
        byte[] inBytes = Files.readAllBytes(Paths.get(nomFichier));
        byte[] texteChiffre = ApiBlowfish.encryptInByte(inBytes, clef);

        String fichierCrypte = nomFichier + ".cryp";
        try (FileOutputStream outputStream = new FileOutputStream(fichierCrypte)) {
            outputStream.write(texteChiffre);
        }

        return Base64.getEncoder().encodeToString(texteChiffre);
}

/**
 * Méthode qui déchiffre un fichier chiffré avec une clé blowfish
 * @param nomFichier : fichier chiffré à déchiffrer
 * @param clef : doit être la même clé utilisée pour chiffrer
 * @return : le contenu déchiffré
 * @throws Exception
 */
static String decrypt(String nomFichier, Key clef) throws Exception{
        byte[] inBytes = Files.readAllBytes(Paths.get(nomFichier));
        byte[] texteDechiffre = ApiBlowfish.decryptInByte(inBytes, clef);
        return new String(texteDechiffre);
}
}
