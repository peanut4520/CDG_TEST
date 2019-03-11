
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ManageBean {
	static List<String> arrSQL = new ArrayList(); // [stdtmove4_carc_ref, unit_code]
	static List<String> arrGetSet = new ArrayList();
	static List<StringBuilder> arrBean = new ArrayList();

	public static void main(String arg[]) {
		// ########### STEP 1 ใส่ตัวที่ต้องการตัด ###################
		String[] replace = { "t1.", "t2.", ",", "t3." };
		// ########### STEP 2 ใส่ค่าจาก SQL ###################
		manageBean(replace);
		manageControlloer();
		classQuery();
	}

	// ####### CREATE Bean #######
	public static void manageBean(String[] replace) {
		System.out.println("------###############################################################-----");
		Scanner in = new Scanner(System.in);
		while (true) {
			String x = in.next();
			arrSQL.add(x);
			if (x.equals("-1")) {
				break;
			}
			for (int i = 0; i < replace.length; i++) {
				x = x.replaceAll(replace[i], "");
			}
//			x = x.replaceAll("t1.", "").replaceAll(",", ""); // ตัด ,
			String[] arrSplit;
			
			arrSplit = x.split("_");
			StringBuilder newStr = new StringBuilder();
			for (int i = 0; i < arrSplit.length; i++) {
				if (i == 0){
					newStr.append(arrSplit[i]);
					continue;
				} else {
					newStr.append(Character.toString(arrSplit[i].charAt(0)).toUpperCase() + arrSplit[i].substring(1));
				}
			}
			arrBean.add(newStr);
			arrGetSet.add(Character.toString(newStr.charAt(0)).toUpperCase() + newStr.substring(1));
		}
		arrBean.forEach(x -> System.out.println(x));
		System.out.println("------###############################################################------");

	}

	public static void manageControlloer() {
		System.out.println(
				"return OutputUtils.toResponse(queryDataManual, result -> NullAwareJsonObjectBuilder.create() ");
		arrBean.forEach(x -> {
			System.out.println(".add(\"" + x + "\", result.get" + Character.toString(x.charAt(0)).toUpperCase()
					+ x.substring(1) + "())");
		});
		System.out.println(".build());");
		System.out.println("------###############################################################-----");
	}

	public static void classQuery() {
		System.out.println("final StringBuilder sql = new StringBuilder(); \n"
				+ "SimpleDynamicQuery query = new SimpleDynamicQuery(sql);");
		for (int i = 0; i < arrGetSet.size(); i++) {
			System.out.println("if (ObjectUtils.isNotNullBlank(bean.get" + arrGetSet.get(i) + "())) {");
			System.out.println("sql.append(\" AND " + arrSQL.get(i) + " = :" + arrBean.get(i) + "\");");
			System.out.println("query.parameter(\"" + arrBean.get(i) + "\", bean.get" + arrGetSet.get(i) + "());");
			System.out.println("}");
		}
		System.out.println("int index = 0;");
		System.out.println("return query.getResultStream(bean, entityManager::createNativeQuery).map(r -> { \n"
				+ "StdttitleBean entity = new StdttitleBean();");
		for (int i = 0; i < arrGetSet.size(); i++) {
			System.out.println("entity.set"+ arrGetSet.get(i)+"(AutoMappingUtils.cast(r[index++], String.class));");
		}
		System.out.println("return entity; \n" + 
				"		}).collect(Collectors.toList());");
		System.out.println("------###############################################################-----");
	}

}
