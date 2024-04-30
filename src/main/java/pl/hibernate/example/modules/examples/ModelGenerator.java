package pl.hibernate.example.modules.examples;

import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import pl.hibernate.example.infrastructure.config.ContextInitializer;
import pl.hibernate.example.modules.entities.BaseModel;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Service
public class ModelGenerator {

    @PersistenceContext
    private EntityManager entityManager;

    public static ModelGenerator getInstance() {
        try {
            return ContextInitializer.getBean(ModelGenerator.class);
        } catch (Exception e) {
            throw (e);
        }
    }

    public String generateCode(BaseModel entity) {
        return generateCode(entity, false);
    }

    public String generateCode(BaseModel entity, boolean commitFlushMode) {
        String tableName = getTableName(entity);
        return generateCode(entity.getName(), tableName, commitFlushMode);
    }


    private String generateCode(String name, String tableName, boolean commitFlushMode) {
        String code = generateCode(name);
        if (code.isEmpty()) {
            code = "DEFAULT";
        }
        Query existsQuery = entityManager.createNativeQuery(
                String.format("SELECT 1 FROM %s WHERE code = '%s' LIMIT 1;", tableName, code));


        if (commitFlushMode) {
            existsQuery.setFlushMode(FlushModeType.COMMIT);
        }


        List resultList = existsQuery.getResultList();


        if (resultList.isEmpty()) {
            return code;
        }


        Query countQuery = entityManager.createNativeQuery(
                String.format("SELECT code FROM %s WHERE code like '%s' ESCAPE '!';", tableName, code + "!_%"));


        if (commitFlushMode) {
            countQuery.setFlushMode(FlushModeType.COMMIT);
        }


        List<String> usercodes = countQuery.getResultList();
        long maxValue = usercodes.stream().map(x -> {
            List<String> parts = Arrays.asList(x.split("_"));
            if (parts.size() > 1 && StringUtils.isNumeric(parts.get(parts.size() - 1))) {
                return parts.get(parts.size() - 1);
            }
            return "0";
        }).mapToLong(Long::parseLong).max().orElse(0);


        return code + '_' + (maxValue + 1);


    }




    private String generateCode(String name) {
        String[] parts = name.split(" ");
        if (parts.length < 2) {
            return preparePart(name, true);
        }
        StringJoiner codeJoiner = new StringJoiner("");
        for (String part : parts) {
            codeJoiner.add(preparePart(part, false));
        }
        return codeJoiner.toString().toUpperCase().trim();
    }


    private String preparePart(String part, boolean isOnlyOne) {
        part = part.replaceAll("[^A-Za-z0-9.,_]", "");
        part = part.replaceAll("[.]", "_");



        if (part.length() < 1) {
            return "";
        }
        if (StringUtils.isNumeric(part) || part.matches("\\d+[_,]\\d+$")
                || (isOnlyOne && !(part.matches("^\\d+") || part.matches(".+\\d+$")))) {
            return part.toUpperCase();
        }
        String result = "";
        for (int i = 0; i < part.length(); i++) {
            char c = part.charAt(i);
            if (Character.isDigit(c)
                    || ((c == '_' || c == ',') && (i + 1 < part.length() && Character.isDigit(part.charAt(i + 1))))) {
                result += c;
            } else {
                result += c;
                break;
            }
        }
        if (part.matches(".+\\d+$")) {
            String endDigits = "";
            for (int i = part.length() - 1; i >= 0; i--) {
                char c = part.charAt(i);
                if (Character.isDigit(c)
                        || ((c == '_' || c == ',') && (i - 1 >= 0 && Character.isDigit(part.charAt(i - 1))))) {
                    endDigits = part.charAt(i) + endDigits;
                } else {
                    break;
                }
            }
            result += endDigits;


        }
        return result.toUpperCase();
    }


    private String getTableName(BaseModel entity) {
        Table table = AnnotationUtils.findAnnotation(entity.getClass(), Table.class);
        if (table == null) {
            String entityTypeName = entity.getName();
            Identifier identifier = new Identifier(entityTypeName, false);
            return new CamelCaseToUnderscoresNamingStrategy().toPhysicalTableName(identifier, null).toString();
        } else {
            return table.name();
        }
    }
}
