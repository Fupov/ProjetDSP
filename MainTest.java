import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


//Merci a ce site pour l'aide : https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
//ainsi qu'a stackoverflow evidemment, comme toujours... !

public class MainTest {
	/*public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
		Batiment c = new Batiment();
		System.out.println(c);
	}*/

	private static final String FILENAME = "C:\\Users\\ing\\IdeaProjects\\JavaProjet\\src\\data.xml";

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer un choix parmi 1, 2 ou 3");
		System.out.println("1: Test simple : on parcourt le fichier, et on affiche le nom et id de chaque composant");
		System.out.println("2: Test un peu plus avance, on parcourt les composants et on affiche, pour ceux qui sont de type ressource, le ou les extracteur permettant de les recuperer");
		System.out.println("3: Test sur la 6eme recette afin d'afficher la liste de ses ingredients en entree et leur quantite");
		int choice = sc.nextInt();
		if (choice==1)	test1();
		if (choice==2)	test2();
		if (choice==3)	test3();

	}

	//Test simple : on parcourt le fichier, et on affiche le nom et id de chaque composant
	public static void test1() 
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try 
		{
			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File(FILENAME));


			//On parcourt tous les composants du fichier
			NodeList list = doc.getElementsByTagName("items");

			//Pour chaque composant...
			for (int temp = 0; temp < list.getLength(); temp++) 
			{
				Node node = list.item(temp);

				if(node.getNodeType() == Node.ELEMENT_NODE) 
				{
					//On regarde le nom et la categorie du composant, et on les affiche
					Element element = (Element) node;
					//Quand le tag lu est unique pour l'objet, on peut faire ainsi
					String category = element.getElementsByTagName("category").item(0).getTextContent();
						String name = element.getElementsByTagName("name").item(0).getTextContent();
					System.out.println(category + " -- " + name);
				}
			}
		}
		catch (ParserConfigurationException | SAXException | IOException e) 
		{
			e.printStackTrace();
		}
	}


	
	//Test un peu plus avance, on parcourt les composants et on affiche, pour ceux qui sont de type ressource, le ou les extracteur permettant de les recuperer
	public static void test2() 
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try 
		{
			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File(FILENAME));


			//On parcourt tous les composants du fichier
			NodeList list = doc.getElementsByTagName("items");

			//Pour chaque composant...
			for (int temp = 0; temp < list.getLength(); temp++) 
			{
				Node node = list.item(temp);

				if(node.getNodeType() == Node.ELEMENT_NODE) 
				{
					//On regarde le nom et la categorie du composant
					Element element = (Element) node;
					//Quand le tag lu est unique pour l'objet, on peut faire ainsi
					String category = element.getElementsByTagName("category").item(0).getTextContent();
					String name = element.getElementsByTagName("name").item(0).getTextContent();
					System.out.print(category + " -- " + name);

					if(category.equals("resource"))
					{
						//Si l'objet est une ressource, on regarde qui permet de miner cette ressource
						System.out.print(" -> ");
						//Quand l'objet peut contenir plusieurs fois le meme tag, on fait ainsi 
						NodeList l = element.getElementsByTagName("minedby");
						for (int i = 0; i < l.getLength(); i++)
						{
							String minedby = l.item(i).getTextContent();
							System.out.print(minedby + " ");
						}
					}
					System.out.println("");
				}
			}
		}
		catch (ParserConfigurationException | SAXException | IOException e) 
		{
			e.printStackTrace();
		}
	}

	//Test sur la 6eme recette afin d'afficher la liste de ses ingredients en entree et leur quantite 
	public static void test3() 
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try 
		{
			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File(FILENAME));


			//On parcourt tous les composants du fichier
			NodeList list = doc.getElementsByTagName("recipes");

			//Pour la sixieme recette
			Node node = list.item(5);

			if(node.getNodeType() == Node.ELEMENT_NODE) 
			{
				//On regarde le nom et la categorie du composant
				Element element = (Element) node;

				String id = element.getElementsByTagName("id").item(0).getTextContent();
				String name = element.getElementsByTagName("name").item(0).getTextContent();
				System.out.println(id + " -- " + name);

				//On recupere les ingredients in de la recette
				Element input = (Element) element.getElementsByTagName("in").item(0);
				
				NodeList liste_ingredient = input.getElementsByTagName("*");
				for(int i=0; i<liste_ingredient.getLength(); i++)
				{
					//On utilise la variable e pour recuperer le nom du tag (qui est le nom de l'item), et la variable input pour recuperer la quantite une fois qu'on connait le nom du tag
					Element e = (Element)liste_ingredient.item(i);
					String id_ingred = e.getNodeName();
					int qte = Integer.parseInt(input.getElementsByTagName(id_ingred).item(0).getTextContent());
			        System.out.println("  >> " + id_ingred + " x" + qte);
				}
			}
		}
		catch (ParserConfigurationException | SAXException | IOException e) 
		{
			e.printStackTrace();
		}
	}
}