package sam.exercises.datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SampleConverter {

    public record Sample(float ts, String[] callStack) {}

    public static class Event {
        private final String type; // "start" or "end"
        private final float ts;
        private final String funcName;

        Event(String type, float ts, String funcName) {
            this.type = type;
            this.ts = ts;
            this.funcName = funcName;
        }

        public String getType() {
            return type;
        }

        public float getTs() {
            return ts;
        }

        public String getFuncName() {
            return funcName;
        }

        @Override
        public String toString() {
            return "Event{\n\ttype=[" + type +
                    "],\n\tts=[" + ts +
                    "],\n\tfuncName=[" + funcName + "]\n}";
        }
    }

    public static List<Event> convertSamplesToEvents(List<Sample> samples) {
        List<Event> result = new ArrayList<>();
        List<Event> stack = new LinkedList<>();

        for (Sample sample : samples) {
            int staIdx = 0;
            int samIdx = 0;
            System.out.println("Comparing");
            while (staIdx < stack.size() && samIdx < sample.callStack.length
                    && stack.get(staIdx).getFuncName().equals(sample.callStack[samIdx])) {
                // Find out where we either run out of one array, for the funcNames are different.
                staIdx++;
                samIdx++;
            }

            // Pop functions off the stack.
            if (staIdx < stack.size() && samIdx < sample.callStack.length)  {
                // Function names are not equal so pop the stack.
                for (int i = 0; i <= stack.size() - staIdx; i++) {
                    Event e = stack.removeLast();
                    result.add(new Event("end", sample.ts, e.funcName));
                }
            }

            // Add new functions to the stack.
            if (samIdx < sample.callStack.length) {
                for (int i = 0; i < sample.callStack.length - samIdx; i++) {
                    Event e = new Event("start", sample.ts, sample.callStack[samIdx + i]);
                    stack.add(e);
                    result.add(e);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Sample> samples = List.of(
                new Sample(12.3f, new String[] { "main" }),
                new Sample(14.4f, new String[] { "main", "outer_function", "inner_function" }),
                new Sample(17.1f, new String[] { "main", "new_function", "inner_function" })
        );

        List<Event> events = convertSamplesToEvents(samples);
        System.out.println(events);
    }

}