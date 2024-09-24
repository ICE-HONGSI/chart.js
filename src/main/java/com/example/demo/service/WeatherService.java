package com.example.demo.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class WeatherService {

    // 하루 시간대별 날씨 데이터를 가져오는 메소드
    public static List<String> getDailyWeather(int x, int y) {
        Map<String, String[]> weatherDataMap = new TreeMap<>(); // 시간대별 데이터를 저장할 TreeMap (시간대 -> 데이터)

        // 여러 시간대의 데이터를 요청할 base_time 목록
        String[] baseTimes = {"0000", "0600", "1200", "1500"};

        for (String baseTime : baseTimes) {
            fetchWeatherDataForBaseTime(x, y, baseTime, weatherDataMap);
        }

        // 출력용 데이터 포맷팅
        List<String> formattedWeatherData = new ArrayList<>();

        // 시간 순으로 정렬된 TreeMap에서 데이터를 포맷팅하여 출력
        for (String[] data : weatherDataMap.values()) {
            String formattedData = String.format("날짜: %s   시간: %s  날씨: %s  기온: %s°C  습도: %s%%",
                    data[0],
                    data[1],
                    data[2] != null ? data[2] : "데이터 없음",
                    data[3] != null ? data[3] : "데이터 없음",
                    data[4] != null ? data[4] : "데이터 없음");
            formattedWeatherData.add(formattedData);
        }

        return formattedWeatherData; // 포맷팅된 데이터 반환
    }

    // 특정 base_time에 대한 날씨 데이터를 가져오는 메소드
    private static void fetchWeatherDataForBaseTime(int x, int y, String baseTime, Map<String, String[]> weatherDataMap) {
        HttpURLConnection con = null;

        try {
            // 현재 날짜의 데이터를 요청
            LocalDate today = LocalDate.now();

            URL url = new URL(
                    "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst"
                            + "?ServiceKey=" + "69YYtu1XspY1rFpEKlo8VJ5mQhO8ab%2BldbDFTsuz7QuxRAucG6e%2BpiDonS0dCWh%2B7V8Mw7cOTXHaFC%2Fs5%2BOuzQ%3D%3D"
                            + "&pageNo=1"
                            + "&numOfRows=1000"
                            + "&base_date=" + today.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                            + "&base_time=" + baseTime
                            + "&nx=" + x
                            + "&ny=" + y
            );

            // API 호출 및 결과 파싱
            con = (HttpURLConnection) url.openConnection();
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(con.getInputStream());

            boolean ok = false; // <resultCode>00</resultCode> 성공 여부

            Element e;
            NodeList ns = doc.getElementsByTagName("header");
            if (ns.getLength() > 0) {
                e = (Element) ns.item(0);
                if ("00".equals(e.getElementsByTagName("resultCode").item(0).getTextContent())) {
                    ok = true; // 성공 여부
                }
            }

            if (ok) {
                String fd, ft; // 예보 날짜와 시간
                String pty = null; // 강수형태
                String cat; // category
                String val; // 예보 값

                ns = doc.getElementsByTagName("item");
                for (int i = 0; i < ns.getLength(); i++) {
                    e = (Element) ns.item(i);

                    fd = e.getElementsByTagName("fcstDate").item(0).getTextContent(); // 예보 날짜
                    ft = e.getElementsByTagName("fcstTime").item(0).getTextContent(); // 예보 시각

                    String[] weatherData = weatherDataMap.getOrDefault(ft, new String[]{fd, ft, null, null, null});

                    // 각 카테고리별 값 처리
                    cat = e.getElementsByTagName("category").item(0).getTextContent();
                    val = e.getElementsByTagName("fcstValue").item(0).getTextContent();

                    if ("PTY".equals(cat)) weatherData[2] = parsePty(val); // 강수형태
                    else if ("T1H".equals(cat)) weatherData[3] = val; // 기온
                    else if ("REH".equals(cat)) weatherData[4] = val; // 습도

                    weatherDataMap.put(ft, weatherData); // 시간대별 데이터 저장
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) con.disconnect(); // 연결 해제
        }
    }

    // 강수형태를 해석하는 메소드
    private static String parsePty(String pty) {
        switch (pty) {
            case "0": return "맑음";
            case "1": return "비";
            case "2": return "비/눈";
            case "3": return "눈";
            case "5": return "빗방울";
            case "6": return "빗방울눈날림";
            case "7": return "눈날림";
            default: return null;
        }
    }
}
