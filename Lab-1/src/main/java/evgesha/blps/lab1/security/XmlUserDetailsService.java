//package evgesha.blps.lab1.security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//public class XmlUserDetailsService implements UserDetailsService {
//
//    private final String USERS_FILE = "D:\\ITMO\\3 КУРС\\БЛПС\\LAB_2\\Lab-1\\src\\main\\java\\evgesha\\blps\\lab1\\security\\users.xml";
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            ClassLoader classLoader = getClass().getClassLoader();
//            InputStream inputStream = classLoader.getResourceAsStream(USERS_FILE);
//            Document document = builder.parse(inputStream);
//            NodeList userList = document.getElementsByTagName("user");
//            for (int i = 0; i < userList.getLength(); i++) {
//                Node userNode = userList.item(i);
//                if (userNode.getNodeType() == Node.ELEMENT_NODE) {
//                    Element userElement = (Element) userNode;
//                    String usernameXml = userElement.getElementsByTagName("username").item(0).getTextContent();
//                    if (usernameXml.equals(username)) {
//                        String passwordXml = userElement.getElementsByTagName("password").item(0).getTextContent();
//                        String roleXml = userElement.getElementsByTagName("role").item(0).getTextContent();
//                        List<GrantedAuthority> authorities = new ArrayList<>();
//                        authorities.add(new SimpleGrantedAuthority(roleXml));
//                        return new User(username, passwordXml, authorities);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            throw new UsernameNotFoundException("Could not load user from XML file", e);
//        }
//        throw new UsernameNotFoundException("User not found");
//    }
//
//}

