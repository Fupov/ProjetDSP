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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


//Merci a ce site pour l'aide : https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
//ainsi qu'a stackoverflow evidemment, comme toujours... !

public class MainTest {
	/*public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
		Batiment c = new Batiment();
		System.out.println(c);
	}*/

	private static final String FILENAME = "data.xml";

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer un choix parmi 1, 2, 3, 4, 5");
		System.out.println("1: Afficher la liste des composants non bâtiment par ordre alphabétique");
		System.out.println("2: Afficher la liste des bâtiments par ordre alphabétique");
		System.out.println("3: Afficher la liste des recettes par ordre alphabétique");
		System.out.println("4: Afficher, pour une usine donnée, toutes les recettes qui lui sont associées");
		System.out.println("5: Afficher, pour un extracteur donné, toutes les ressources qu’il peut extraire");
		int choice = sc.nextInt();
		if (choice==1)	test4();
		if (choice==2)	test5();
		if (choice==3)	test6();
		if (choice==4)	test7();
		if (choice==5)	test8();
	}


	public static void test4()
	{
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(FILENAME));
			NodeList list = doc.getElementsByTagName("items");

			List<String> composants = new ArrayList();
			//Pour chaque composant...
			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					//On regarde le nom et la categorie du composant, et on les affiche
					Element element = (Element) node;

					if (!element.getElementsByTagName("category").item(0).getTextContent().equalsIgnoreCase("buildings")) {

						String id = element.getElementsByTagName("id").item(0).getTextContent();
						String name = element.getElementsByTagName("name").item(0).getTextContent();
						String category = element.getElementsByTagName("category").item(0).getTextContent();
						Composant composant = new Composant(id, name, category);
						composants.add(composant.toString());
					}
				}
			}
			Collections.sort(composants);
			for (String c : composants) {
				System.out.println(c);
			}
		}catch (ParserConfigurationException | SAXException | IOException e)
		{
			e.printStackTrace();
		}


	}
	public static void test5()
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try{
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new File(FILENAME));
			NodeList list = doc.getElementsByTagName("items");

		List<String> composants = new ArrayList();
		//Pour chaque composant...
		for (int temp = 0; temp < list.getLength(); temp++)
		{
			Node node = list.item(temp);

			if(node.getNodeType() == Node.ELEMENT_NODE)
			{
				//On regarde le nom et la categorie du composant, et on les affiche
				Element element = (Element) node;

				if(element.getElementsByTagName("category").item(0).getTextContent().equalsIgnoreCase("buildings"))
				{

					String id = element.getElementsByTagName("id").item(0).getTextContent();
					String name = element.getElementsByTagName("name").item(0).getTextContent();
					String category = element.getElementsByTagName("category").item(0).getTextContent();
					Composant composant = new Composant(id, name,category);
					composants.add(composant.toString());
				}
			}
		}
		Collections.sort(composants);
		for(String c : composants){
			System.out.println(c);
		}
		}catch (ParserConfigurationException | SAXException | IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void test6() {
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(FILENAME));
			NodeList list = doc.getElementsByTagName("recipes");
			List<String> recettes = new ArrayList();
			//Pour chaque ressource...
			for (int temp = 0; temp < list.getLength(); temp++)
			{
				Node node = list.item(temp);

				if(node.getNodeType() == Node.ELEMENT_NODE)
				{
					//On regarde le nom et la categorie du composant, et on les affiche
					Element element = (Element) node;

					String id = element.getElementsByTagName("id").item(0).getTextContent();
					String name = element.getElementsByTagName("name").item(0).getTextContent();
					double time= Double.parseDouble((((Element) element.getElementsByTagName("time").item(0)).getTextContent()));
					String producer= ((Element) element.getElementsByTagName("producers").item(0)).getTextContent();
					Element input = (Element) element.getElementsByTagName("in").item(0);
					NodeList liste_ingredient = input.getElementsByTagName("*");
					double qte;
					String id_ingred;
					ArrayList<String> in=new ArrayList<String>();
					for(int i=0; i<liste_ingredient.getLength(); i++)
					{
						//On utilise la variable e pour recuperer le nom du tag (qui est le nom de l'item), et la variable input pour recuperer la quantite une fois qu'on connait le nom du tag
						Element e = (Element)liste_ingredient.item(i);
						id_ingred = e.getNodeName();
						qte = Double.parseDouble(input.getElementsByTagName(id_ingred).item(0).getTextContent());
						in.add(id_ingred.toString());
						in.add(i, String.valueOf(qte));
					}
					Recette recette = new Recette(id,name,time,in,producer);
					recettes.add(recette.toString());
				}
			}

			Collections.sort(recettes);
			for(String c : recettes){
				System.out.println(c);
			}
		}catch (ParserConfigurationException | SAXException | IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void test7()
	{

		try{DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(FILENAME));
			NodeList list = doc.getElementsByTagName("items");

			List<String> usines = new ArrayList();
			List<String> recettes = new ArrayList();

			//Pour chaque composant...
			for (int temp = 0; temp < list.getLength(); temp++)
			{
				Node node = list.item(temp);

				if(node.getNodeType() == Node.ELEMENT_NODE)
				{
					//On regarde le nom et la categorie du composant, et on les affiche
					Element element = (Element) node;

					if(element.getElementsByTagName("category").item(0).getTextContent().equalsIgnoreCase("buildings"))
					{

						String id = element.getElementsByTagName("id").item(0).getTextContent();
						NodeList list2 = doc.getElementsByTagName("recipes");
						for (int temp2 = 0; temp2 < list2.getLength(); temp2++)
						{
							Node node2 = list2.item(temp2);

							if(node2.getNodeType() == Node.ELEMENT_NODE)
							{
								Element element2 = (Element) node2;
								String id2 = element2.getElementsByTagName("id").item(0).getTextContent();
								String name = element2.getElementsByTagName("name").item(0).getTextContent();
								double time= Double.parseDouble((element2.getElementsByTagName("time").item(0)).getTextContent());
								String producer=element2.getElementsByTagName("producers").item(0).getTextContent();
								Element input2 = (Element) element2.getElementsByTagName("in").item(0);
								NodeList liste_ingredient = input2.getElementsByTagName("*");
								Double qte;
								String id_ingred;
								ArrayList<String> in=new ArrayList<String>();
								for(int i=0; i<liste_ingredient.getLength(); i++)
								{
									//On utilise la variable e pour recuperer le nom du tag (qui est le nom de l'item), et la variable input pour recuperer la quantite une fois qu'on connait le nom du tag
									Element e = (Element)liste_ingredient.item(i);
									id_ingred = e.getNodeName();
									Ressource ing=new Ressource(id);
									qte = Double.parseDouble(input2.getElementsByTagName(id_ingred).item(0).getTextContent());
									in.add(id_ingred.toString()+" "+String.valueOf(qte));
								}
								Recette recette = new Recette(id2,name,time,in,producer);
								recettes.add(recette.toString());
								if(id.equals(producer)){
									usines.add(id+" -- "+recette.toString());
								}
							}
						}
					}
				}
			}
			for(String c : usines){
				System.out.println(c);
			}
		}catch (ParserConfigurationException | SAXException | IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void test8()
	{

		try{DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(FILENAME));
			NodeList list = doc.getElementsByTagName("items");

			List<String> extractor = new ArrayList();
			//Pour chaque composant...
			for (int temp = 0; temp < list.getLength(); temp++)
			{
				Node node = list.item(temp);

				if(node.getNodeType() == Node.ELEMENT_NODE)
				{
					//On regarde le nom et la categorie du composant, et on les affiche
					Element element = (Element) node;

					if(element.getElementsByTagName("category").item(0).getTextContent().equalsIgnoreCase("buildings"))
					{
						String id = element.getElementsByTagName("id").item(0).getTextContent();
						NodeList list2 = doc.getElementsByTagName("items");
						for (int temp2 = 0; temp2 < list2.getLength(); temp2++)
						{
							Node node2 = list2.item(temp2);
							if(node2.getNodeType() == Node.ELEMENT_NODE) {
								Element element2 = (Element) node2;
								if (element2.getElementsByTagName("category").item(0).getTextContent().equalsIgnoreCase("resource")) {
									String id2 = element2.getElementsByTagName("id").item(0).getTextContent();
									String name = element2.getElementsByTagName("name").item(0).getTextContent();
									String minedby=element2.getElementsByTagName("minedby").item(0).getTextContent();
									Ressource ressource = new Ressource(id2,name,minedby);
									if (id.equals(minedby)) {
										extractor.add(id + " - " + ressource);
									}
								}
							}
						}
					}
				}
			}
			for(String c : extractor){
				System.out.println(c);
			}
		}catch (ParserConfigurationException | SAXException | IOException e)
		{
			e.printStackTrace();
		}
	}
}