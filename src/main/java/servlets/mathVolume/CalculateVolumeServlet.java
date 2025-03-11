package servlets.mathVolume;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CalculateVolumeServlet extends HttpServlet {
        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws  IOException {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Чтение JSON из запроса
            CalculationData data = objectMapper.readValue(request.getReader(), CalculationData.class);

            // Расчет объема
            double volume = 0;
            String error = null;

            switch (data.figure) {
                case "параллелепипед":
                    if (data.edges.length == 3) {
                        volume = data.edges[0] * data.edges[1] * data.edges[2];
                    } else {
                        error = "Для параллелепипеда нужно ввести 3 значения длин граней.";
                    }
                    break;
                case "куб":
                    if (data.edges.length == 1) {
                        volume = Math.pow(data.edges[0], 3);
                    } else {
                        error = "Для куба нужно ввести 1 значение длины грани.";
                    }
                    break;
                case "сфера":
                case "шар":
                    if (data.edges.length == 1) {
                        volume = (4.0 / 3.0) * Math.PI * Math.pow(data.edges[0], 3);
                    } else {
                        error = "Для сферы/шара нужно ввести 1 значение радиуса.";
                    }
                    break;
                case "тетраэдр":
                    if (data.edges.length == 1) {
                        volume = (Math.pow(data.edges[0], 3)) / (6 * Math.sqrt(2));
                    } else {
                        error = "Для тетраэдра нужно ввести 1 значение длины грани.";
                    }
                    break;
                case "тор":
                    if (data.edges.length == 2) {
                        double R = data.edges[0];
                        double r = data.edges[1];
                        volume = 2 * Math.pow(Math.PI, 2) * R * Math.pow(r, 2);
                    } else {
                        error = "Для тора нужно ввести 2 значения: радиус тора и радиус сечения.";
                    }
                    break;
                case "эллипсоид":
                    if (data.edges.length == 3) {
                        double a = data.edges[0];
                        double b = data.edges[1];
                        double c = data.edges[2];
                        volume = (4.0 / 3.0) * Math.PI * a * b * c;
                    } else {
                        error = "Для эллипсоида нужно ввести 3 значения длин полуосей.";
                    }
                    break;
                default:
                    error = "Выберите фигуру из списка.";
                    break;
            }

            // Округление результата
            if (error == null) {
                volume = Math.round(volume / data.accuracy) * data.accuracy;
            }

            // Формирование ответа
            PrintWriter out = response.getWriter();
            CalculationResult result = new CalculationResult(volume, error);
            out.print(objectMapper.writeValueAsString(result));
            out.flush();
        }

        // Вспомогательные классы для данных
        private static class CalculationData {
            private String figure;
            private double[] edges;
            private double accuracy;

            public String getFigure() {
                return figure;
            }

            public void setFigure(String figure) {
                this.figure = figure;
            }

            public double[] getEdges() {
                return edges;
            }

            public void setEdges(double[] edges) {
                this.edges = edges;
            }

            public double getAccuracy() {
                return accuracy;
            }

            public void setAccuracy(double accuracy) {
                this.accuracy = accuracy;
            }
        }

        private static class CalculationResult {
            private Double volume;
            private String error;

            public CalculationResult(Double volume, String error) {
                this.volume = volume;
                this.error = error;
            }

            public Double getVolume() {
                return volume;
            }

            public String getError() {
                return error;
            }
        }
    }

