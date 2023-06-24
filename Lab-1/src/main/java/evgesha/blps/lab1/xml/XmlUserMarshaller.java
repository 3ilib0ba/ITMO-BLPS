package evgesha.blps.lab1.xml;

import evgesha.blps.lab1.entity.User;
import lombok.Getter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class XmlUserMarshaller {
    private List<User> users;
    private static String USER_XML_FILE_PATH = "./users.xml";

    public void writeUsersToXml(List<User> users) throws JAXBException, IOException {
        var userS = new UserS(users);
        JAXBContext context = JAXBContext.newInstance(UserS.class);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(userS, new File(USER_XML_FILE_PATH));
    }
    public List<User> readAllUsersFromXml() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(UserS.class);
        return ((UserS) context.createUnmarshaller()
                .unmarshal(new FileReader(USER_XML_FILE_PATH))).getUsers();
    }


    public void readAllUsersFromXmlIntoMemory() throws JAXBException, IOException {
        var f = new File(USER_XML_FILE_PATH);
        if (!f.exists()) {
            var created = f.createNewFile();
            if (!created) throw new IOException("can't create file to store users");
            writeUsersToXml(new ArrayList<User>());
        }
        var xmlUsers = readAllUsersFromXml();
        this.users = xmlUsers == null ? new ArrayList<>() : xmlUsers;
    }

    public void addUserIntoXmlFile(User user) throws JAXBException, IOException {
        this.users.add(user);
        writeUsersToXml(users);
    }
}
