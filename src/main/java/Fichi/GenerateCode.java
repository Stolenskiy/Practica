package Fichi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GenerateCode {

    private static final String repositoryDir = "D:\\Programing\\Java\\Practica\\src\\main\\java\\ua\\nic\\Practica\\repository";
    private static final String serviceDir = "D:\\Programing\\Java\\Practica\\src\\main\\java\\ua\\nic\\Practica\\service";
    private static final String restControllerDir = "D:\\Programing\\Java\\Practica\\src\\main\\java\\ua\\nic\\Practica\\controller";
    private static final String viewControllerDir = "D:\\Programing\\Java\\Cursova\\src\\main\\java\\ua\\nic\\Cursova\\view\\controller";

    public static String dontHaveSpace (String mysql) {
        //`graduation date of the General Academy` date null,
        String returnSQL = "";
        for (String lineBad : mysql.split("\n")) {
            for (String line : lineBad.split("\\(")) {
                boolean b = false;
                for (char simvol : line.toCharArray()) {
                    if (line.trim().toCharArray()[0] == '`') {
                        if (simvol == '`') b = !b;
                        if (b && simvol == ' ') simvol = '_';
                    }
                    returnSQL += simvol;
                }
                returnSQL += '(';
            }
            returnSQL += '\n';
        }
        return returnSQL.replaceAll("\\(\n", "\n");

    }

    public static void generateRepository (String tablesName) {
        for (String tName : tablesName.split("\n")) {
            String repository = "";
            tName = tName.replaceAll("^.", String.valueOf(tName.toUpperCase().toCharArray()[0]));
            FileWriter fileWriter;
            String dir = repositoryDir;
            File file = new File(dir, "I" + tName + "Repository.java");

            try {
                file.createNewFile();
                fileWriter = new FileWriter(file, false);
                repository = "package ua.nic.Cursova.repository;\n" +
                        "\n" +
                        "import org.springframework.data.jpa.repository.JpaRepository;\n" +
                        "import ua.nic.Cursova.model." + tName + "Entity;\n" +
                        "\n" +
                        "/*\n" +
                        "    Для того, щоб забезпечити взаємодію із БД,\n" +
                        "    потрібно створити репозиторій для кожної сущності\n" +
                        "*/\n";
                repository += "public interface I" + tName +
                        "Repository extends JpaRepository<" + tName + "Entity, Long> {} \n";
                fileWriter.write(repository);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void generateService (String tablesName) {
        for (String tName : tablesName.split("\n")) {
            String service = "";
            String lowerTName = tName;
            tName = tName.replaceAll("^.", String.valueOf(tName.toUpperCase().toCharArray()[0]));
            FileWriter fileWriter;
            String dir = serviceDir;
            File file = new File(dir, tName + "Service.java");

            try {
                file.createNewFile();
                fileWriter = new FileWriter(file, false);
                service = "";
                service += "package ua.nic.Cursova.service;\n" +
                        "\n" +
                        "import ua.nic.Cursova.model." + tName + "Entity;\n" +
                        "import ua.nic.Cursova.repository.I" + tName + "Repository;\n" +
                        "import org.springframework.beans.factory.annotation.Autowired;\n" +
                        "import org.springframework.stereotype.Service;\n" +
                        "\n" +
                        "import java.util.List;\n" +
                        "import java.util.logging.Logger;\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "@Service\n" +
                        "public class " + tName + "Service implements IEntityService {\n" +
                        "    \n" +
                        "    private static Logger log = Logger.getLogger(" + tName + "Service.class.getName());\n" +
                        "    @Autowired\n" +
                        "    I" + tName + "Repository " + lowerTName + "Repository;\n" +
                        "\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public " + tName + "Entity getById(Long id) {\n" +
                        "        log.info(\"In " + tName + "Service getById{{}\" + id);\n" +
                        "        return " + lowerTName + "Repository.getOne(id);\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void add(Object " + lowerTName + ") {\n" +
                        "        log.info(\"In " + tName + "Service save{}\" + " + lowerTName + ");\n" +
                        "\n" +
                        "        " + lowerTName + "Repository.save((" + tName + "Entity) " + lowerTName + ");\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void delete(Long id) {\n" +
                        "        log.info(\"In " + tName + "Service delete{}\" + id);\n" +
                        "        " + lowerTName + "Repository.deleteById(id);\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public List getAll() {\n" +
                        "       return " + lowerTName + "Repository.findAll();\n" +
                        "    }\n" +
                        "}\n";


                fileWriter.write(service);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void generateRestController (String tablesName) {
        for (String tName : tablesName.split("\n")) {
            String controller = "";
            String lowerTName = tName;
            tName = tName.replaceAll("^.", String.valueOf(tName.toUpperCase().toCharArray()[0]));
            FileWriter fileWriter;
            String dir = restControllerDir;
            File file = new File(dir, tName + "RestController.java");

            try {
                file.createNewFile();
                fileWriter = new FileWriter(file, false);
                controller = "";
                controller += "package ua.nic.Cursova.rest.controller;\n" +
                        "\n" +
                        "import ua.nic.Cursova.model." + tName + "Entity;\n" +
                        "import ua.nic.Cursova.service.IEntityService;\n" +
                        "import org.springframework.beans.factory.annotation.Autowired;\n" +
                        "import org.springframework.http.HttpHeaders;\n" +
                        "import org.springframework.http.HttpStatus;\n" +
                        "import org.springframework.http.MediaType;\n" +
                        "import org.springframework.http.ResponseEntity;\n" +
                        "import org.springframework.web.bind.annotation.*;\n" +
                        "import javax.validation.Valid;\n" +
                        "import java.util.List;\n" +
                        "\n" +
                        "@RestController\n" +
                        "@RequestMapping(\"/" + lowerTName + "\")\n" +
                        "public class " + tName + "RestController {\n" +
                        "    @Autowired\n" +
                        "    private IEntityService " + lowerTName + "Service;\n" +
                        "\n" +
                        "\n" +
                        "    @RequestMapping(value = \"{id}\", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)\n" +
                        "    public ResponseEntity<" + tName + "Entity> get" + tName + "(@PathVariable(\"id\") Long " + lowerTName + "Id) {\n" +
                        "        System.out.println(\"Get\");\n" +
                        "        if (" + lowerTName + "Id == null)\n" +
                        "            return new ResponseEntity(HttpStatus.BAD_REQUEST);\n" +
                        "\n" +
                        "        " + tName + "Entity " + lowerTName + " = (" + tName + "Entity) this." + lowerTName + "Service.getById(" + lowerTName + "Id);\n" +
                        "\n" +
                        "        if (" + lowerTName + " == null)\n" +
                        "            return new ResponseEntity(HttpStatus.NOT_FOUND);\n" +
                        "\n" +
                        "        return new ResponseEntity(" + lowerTName + ", HttpStatus.OK);\n" +
                        "    }\n" +
                        "\n" +
                        "    @RequestMapping(value = \"\", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)\n" +
                        "    public ResponseEntity<" + tName + "Entity> add" + tName + "(@RequestBody @Valid " + tName + "Entity " + lowerTName + ") {\n" +
                        "        HttpHeaders httpHeaders = new HttpHeaders();\n" +
                        "        System.out.println(\"add\");\n" +
                        "        if (" + lowerTName + " == null)\n" +
                        "            return new ResponseEntity(HttpStatus.BAD_REQUEST);\n" +
                        "\n" +
                        "        this." + lowerTName + "Service.add(" + lowerTName + ");\n" +
                        "\n" +
                        "        return new ResponseEntity(" + lowerTName + ", httpHeaders, HttpStatus.CREATED);\n" +
                        "    }\n" +
                        "\n" +
                        "    @RequestMapping(value = \"{id}\", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)\n" +
                        "    public ResponseEntity<" + tName + "Entity> update" + tName + "(@RequestBody @Valid " + tName + "Entity " + lowerTName + ", @PathVariable(\"id\") Long " + lowerTName + "Id) {\n" +
                        "        HttpHeaders httpHeaders = new HttpHeaders();\n" +
                        "        System.out.println(\"update\");\n" +
                        "        if (" + lowerTName + " == null)\n" +
                        "            return new ResponseEntity(HttpStatus.BAD_REQUEST);\n" +
                        "        this." + lowerTName + "Service.delete(" + lowerTName + "Id);\n" +
                        "        this." + lowerTName + "Service.add(" + lowerTName + ");\n" +
                        "\n" +
                        "        return new ResponseEntity(" + lowerTName + ", httpHeaders, HttpStatus.OK);\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    @RequestMapping(value = \"{id}\", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)\n" +
                        "    public ResponseEntity<" + tName + "Entity> delete" + tName + "(@PathVariable(\"id\") Long " + lowerTName + "Id) {\n" +
                        "        System.out.println(\"delete\");\n" +
                        "        " + tName + "Entity " + lowerTName + " = (" + tName + "Entity) this." + lowerTName + "Service.getById(" + lowerTName + "Id);\n" +
                        "\n" +
                        "        if (" + lowerTName + " == null)\n" +
                        "            return new ResponseEntity(HttpStatus.NOT_FOUND);\n" +
                        "\n" +
                        "        this." + lowerTName + "Service.delete(" + lowerTName + "Id);\n" +
                        "\n" +
                        "        return new ResponseEntity(HttpStatus.NO_CONTENT);\n" +
                        "    }\n" +
                        "\n" +
                        "    @RequestMapping(value = \"\", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)\n" +
                        "    public ResponseEntity<List<" + tName + "Entity>> getAll" + tName + "s() {\n" +
                        "        List " + lowerTName + " = this." + lowerTName + "Service.getAll();\n" +
                        "        System.out.println(\"GetAll\");\n" +
                        "        return new ResponseEntity(" + lowerTName + ", HttpStatus.OK);\n" +
                        "    }\n" +
                        "\n" +
                        "}";


                fileWriter.write(controller);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void generateViewController (String tablesName) {
        for (String tName : tablesName.split("\n")) {
            String controller = "";
            String lowerTName = tName;
            tName = tName.replaceAll("^.", String.valueOf(tName.toUpperCase().toCharArray()[0]));
            FileWriter fileWriter;
            String dir = viewControllerDir;
            File file = new File(dir, tName + "ViewController.java");

            try {
                file.createNewFile();
                fileWriter = new FileWriter(file, false);
                controller = "";
                controller += "package ua.nic.Cursova.view.controller;\n" +
                        "\n" +
                        "import org.springframework.beans.factory.annotation.Autowired;\n" +
                        "import org.springframework.stereotype.Controller;\n" +
                        "import org.springframework.ui.Model;\n" +
                        "import org.springframework.validation.BindingResult;\n" +
                        "import org.springframework.web.bind.annotation.*;\n" +
                        "import org.springframework.web.servlet.ModelAndView;\n" +
                        "import ua.nic.Cursova.model." + tName + "Entity;\n" +
                        "import ua.nic.Cursova.service.IEntityService;\n" +
                        "\n" +
                        "import javax.validation.Valid;\n" +
                        "\n" +
                        "@Controller\n" +
                        "public class " + tName + "ViewController {\n" +
                        "    @Autowired\n" +
                        "    private IEntityService " + lowerTName + "Service;\n" +
                        "\n" +
                        "    @GetMapping(\"/" + lowerTName + "List\")\n" +
                        "    public String getAll" + tName + " (Model model) {\n" +
                        "        model.addAttribute(\"" + lowerTName + "List\", " + lowerTName + "Service.getAll());\n" +
                        "        model.addAttribute(\"" + lowerTName + "Entity\", new " + tName + "Entity());\n" +
                        "        return \"" + lowerTName + "List.html\";\n" +
                        "    }\n" +
                        "\n" +
                        "    @RequestMapping(value = \"/" + lowerTName + "List\", method = RequestMethod.POST, params = \"action=delete\")\n" +
                        "    ModelAndView delete" + tName + " (\n" +
                        "            ModelAndView modelAndView,\n" +
                        "            @Valid " + tName + "Entity " + lowerTName + "Entity,\n" +
                        "            BindingResult result) {\n" +
                        "        " + lowerTName + "Service.delete(" + lowerTName + "Entity.getId());\n" +
                        "        modelAndView.setViewName(\"redirect:/" + lowerTName + "List\");\n" +
                        "        return modelAndView;\n" +
                        "    }\n" +
                        "    @RequestMapping(value = \"/" + lowerTName + "List\", method = RequestMethod.POST, params = \"action=add\")\n" +
                        "    ModelAndView add" + tName + " (\n" +
                        "            ModelAndView modelAndView,\n" +
                        "            @Valid " + tName + "Entity " + lowerTName + "Entity,\n" +
                        "            BindingResult result) {\n" +
                        "        " + lowerTName + "Service.save(" + lowerTName + "Entity);\n" +
                        "        modelAndView.setViewName(\"redirect:/" + lowerTName + "List\");\n" +
                        "        return modelAndView;\n" +
                        "    }\n" +
                        "    @RequestMapping(value = \"/" + lowerTName + "List\", method = RequestMethod.POST, params = \"action=save\")\n" +
                        "    ModelAndView update" + tName + " (\n" +
                        "            ModelAndView modelAndView,\n" +
                        "            @Valid " + tName + "Entity " + lowerTName + "Entity,\n" +
                        "            BindingResult result) {\n" +
                        "\n" +
                        "        if (!result.hasErrors()) {\n" +
                        "            " + lowerTName + "Service.delete(" + lowerTName + "Entity.getId());\n" +
                        "            " + lowerTName + "Service.save(" + lowerTName + "Entity);\n" +
                        "            modelAndView.getModel().put(\"" + lowerTName + "\", " + lowerTName + "Entity);\n" +
                        "            modelAndView.setViewName(\"redirect:/" + lowerTName + "List\");\n" +
                        "        }\n" +
                        "        return modelAndView;\n" +
                        "    }\n" +
                        "\n" +
                        "}\n" +
                        "\n";


                fileWriter.write(controller);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createHtml (String tableName, String tableFields, String tableTypes) {

        String controller = "";
        String lowerTName = tableName;
        tableName = tableName.replaceAll("^.", String.valueOf(tableName.toUpperCase().toCharArray()[0]));
        FileWriter fileWriter;
        String dir = "D:\\Programing\\Java\\Cursova\\src\\main\\resources\\templates";
        File file = new File(dir, tableName + "List.html");

        try {
            file.createNewFile();
            fileWriter = new FileWriter(file, false);
            controller = "";

            controller += "<!DOCTYPE html>\n" +
                    "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "    <link rel=\"stylesheet\" type=\"text/css\" media=\"all\" href=\"css/mainStyle.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<table>\n" +
                    "    <tr>\n";
            for (String field : tableFields.split("\n")) {
                controller += "        <th>" + field + "</th>\n";
            }

            controller += "    </tr>\n" +
                    "\n" +
                    "    <tr th:each=\"" + lowerTName + " : ${" + lowerTName + "List}\">\n" +
                    "        <form th:action=\"@{/"
                    + lowerTName + "List}\" th:object=\"${" + lowerTName + "}\" method=\"post\">\n" +
                    "            <td style=\"border-left: 2px solid #040014\"><input type=\"number\" th:value=\"${"
                    + lowerTName + ".id}\" name=\"id\" readonly/></td>\n" +
                    "\n";

            for (int i = 1; i < tableFields.split("\n").length; i++) {
                controller += "            <td><input type=\"" + tableTypes.split("\n")[i]
                        + "\" th:value=\"${" + lowerTName + "." + tableFields.split("\n")[i] + "}\" name=\""
                        + tableFields.split("\n")[i] + "\"/></td>\n";

            }
            controller += "            <td class=\"ButtonCell\">\n" +
                    "                <button type=\"submit\" name=\"action\" value=\"save\" class=\"BluBtn\">Save</button>\n" +
                    "            </td>\n" +
                    "            <td class=\"ButtonCell\">\n" +
                    "                <button type=\"submit\" name=\"action\" value=\"delete\" class=\"RedBtn\">Delete</button>\n" +
                    "            </td>\n" +

                    "        </form>\n" +
                    "\n" +
                    "    </tr>\n" +
                    "\n" +
                    "    <form th:action=\"@{/" + lowerTName + "List}\" th:object=\"${" + lowerTName
                    + "Entity}\" method=\"post\">\n" +
                    "        <tr>\n";
            for (int i = 0; i < tableFields.split("\n").length; i++) {
                if (i == 0) controller += "            <td style=\"border-left: 2px solid #040014\">\n";
                else controller += "            <td>";
                controller += "                <input type=\"" + tableTypes.split("\n")[i]
                        + "\" th:value=\"${" + lowerTName
                        + "Entity." + tableFields.split("\n")[i] + "}\" name=\""
                        + tableFields.split("\n")[i] + "\"/></td>\n";
            }
            controller += "            <td class=\"ButtonCell\">\n" +
                    "                <button type=\"submit\" name=\"action\" value=\"add\" class=\"BluBtn LastBtn\">Add</button>\n" +
                    "            </td>\n" +
                    "        <tr/>\n" +
                    "\n" +
                    "    </form>\n" +
                    "</table>\n" +
                    "</body>\n" +
                    "</html>";


            fileWriter.write(controller);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateHelpCode (String tablesName) {
        String helpC = "";
        FileWriter fileWriter;
        String dir = "D:\\Programing\\Java\\Cursova\\src\\main\\java\\Fichi";
        File file = new File(dir, "HelpCode.java");

        try {
            file.createNewFile();
            fileWriter = new FileWriter(file, false);
            helpC = "";
            helpC += "package Fichi;\n" +
                    "\n" +
                    "import ua.nic.Cursova.model.*;\n" +
                    "\n" +
                    "import java.util.ArrayList;\n" +
                    "import java.util.List;\n" +
                    "\n" +
                    "public class HelpCode {\n" +
                    "    public static List<String[]> helpCode(){\n" +
                    "        String s;\n" +
                    "        String s1;\n" +
                    "        List<String[]> retList = new ArrayList();\n" +
                    "        String[] ret = new String[2];\n" +
                    "        ret[0] = \"\";\n" +
                    "        ret[1] = \"\";\n";
            for (String tName : tablesName.split("\n")) {
                String lowerTName = tName;
                tName = tName.replaceAll("^.", String.valueOf(tName.toUpperCase().toCharArray()[0]));

                helpC += "        ret[0] += " + tName + "Entity.class.getDeclaredFields()[0].getName();\n" +
                        "        s = " + tName + "Entity.class.getDeclaredFields()[0].getType().getName().toLowerCase();\n" +
                        "        if (\"long\".equals(s)) {\n" +
                        "            ret[1] += \"number\";\n" +
                        "        } else if (\"java.lang.long\".equals(s)) {\n" +
                        "            ret[1] += \"number\";\n" +
                        "        } else if (\"java.lang.byte\".equals(s)) {\n" +
                        "            ret[1] += \"number\";\n" +
                        "        } else if (\"java.sql.date\".equals(s)) {\n" +
                        "            ret[1] += \"date\";\n" +
                        "        } else if (\"java.lang.string\".equals(s)) {\n" +
                        "            ret[1] += \"text\";\n" +
                        "        }\n" +
                        "\n" +
                        "        for (int i = 1; i < " + tName + "Entity.class.getDeclaredFields().length; i++) {\n" +
                        "            ret[0] += \"\\n\" + " + tName + "Entity.class.getDeclaredFields()[i].getName();\n" +
                        "        s1 = " + tName + "Entity.class.getDeclaredFields()[i].getType().getName().toLowerCase();\n" +
                        "            if (\"long\".equals(s1)) {\n" +
                        "                ret[1] += \"\\n\" + \"number\";\n" +
                        "            } else if (\"java.lang.long\".equals(s1)) {\n" +
                        "                ret[1] += \"\\n\" + \"number\";\n" +
                        "            } else if (\"java.lang.byte\".equals(s1)) {\n" +
                        "                ret[1] += \"\\n\" + \"number\";\n" +
                        "            } else if (\"java.sql.date\".equals(s1)) {\n" +
                        "                ret[1] += \"\\n\" + \"date\";\n" +
                        "            } else if (\"java.lang.string\".equals(s1)) {\n" +
                        "                ret[1] += \"\\n\" + \"text\";\n" +
                        "            }\n" +
                        "        }\n" +
                        "        retList.add(ret.clone());\n" +
                        "        ret[0] = \"\";\n" +
                        "        ret[1] = \"\";\n" +
                        "        \n";
            }

            helpC += "        return retList;\n" +
                    "    }\n" +
                    "}\n";


            fileWriter.write(helpC);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateViewHtml (String tablesName) {
        List<String[]> fields = HelpCode.helpCode();
        for (int i = 0; i < fields.size(); i++) {
            createHtml(tablesName.split("\n")[i], fields.get(i)[0], fields.get(i)[1]);
        }
    }
    
    public static void main (String[] args) {
        String tables = "located\n" +
                "tradingFloor\n" +
                "image\n" +
                "images";
        generateRepository(tables);
        generateService(tables);
        generateRestController(tables);
//        generateViewController(tables);
//        generateViewHtml(tables);

//        generateHelpCode(tables);
//        generateViewHtml(tables);



    }
}

