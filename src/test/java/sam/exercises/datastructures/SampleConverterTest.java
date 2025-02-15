package sam.exercises.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SampleConverterTest {

    @Test
    public void samplesAreAddedToResult_SingleSample() {
        List<SampleConverter.Event> result = SampleConverter.convertSamplesToEvents(
                List.of(new SampleConverter.Sample(
                        14.4f, new String[] { "main", "outer_function", "inner_function" })));
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("start", result.get(0).getType());
        Assertions.assertEquals(14.4f, result.get(0).getTs());
        Assertions.assertEquals("main", result.get(0).getFuncName());
        Assertions.assertEquals("start", result.get(1).getType());
        Assertions.assertEquals(14.4f, result.get(1).getTs());
        Assertions.assertEquals("outer_function", result.get(1).getFuncName());
        Assertions.assertEquals("start", result.get(2).getType());
        Assertions.assertEquals(14.4f, result.get(2).getTs());
        Assertions.assertEquals("inner_function", result.get(2).getFuncName());
    }

    @Test
    public void samplesAreAddedToResult_MultipleSamples() {
        List<SampleConverter.Event> result = SampleConverter.convertSamplesToEvents(List.of(
                new SampleConverter.Sample(
                        10.4f, new String[] { "main" }),
                new SampleConverter.Sample(
                        11.5f, new String[] { "main", "outer_function" }),
                new SampleConverter.Sample(
                        12.6f, new String[] { "main", "outer_function", "inner_function" })
        ));
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("start", result.get(0).getType());
        Assertions.assertEquals(10.4f, result.get(0).getTs());
        Assertions.assertEquals("main", result.get(0).getFuncName());
        Assertions.assertEquals("start", result.get(1).getType());
        Assertions.assertEquals(11.5f, result.get(1).getTs());
        Assertions.assertEquals("outer_function", result.get(1).getFuncName());
        Assertions.assertEquals("start", result.get(2).getType());
        Assertions.assertEquals(12.6f, result.get(2).getTs());
        Assertions.assertEquals("inner_function", result.get(2).getFuncName());
    }

    @Test
    public void samplesAreAddedToResult_MultipleSamplesWithEnds() {
        List<SampleConverter.Event> result = SampleConverter.convertSamplesToEvents(List.of(
                new SampleConverter.Sample(
                        10.7f, new String[] { "main" }),
                new SampleConverter.Sample(
                        11.6f, new String[] { "main", "outer_function", "inner_function" }),
                new SampleConverter.Sample(
                        12.5f, new String[] { "main", "new_function" })
        ));
        System.out.print(result);
        Assertions.assertEquals(6, result.size());
        Assertions.assertEquals("start", result.get(0).getType());
        Assertions.assertEquals(10.7f, result.get(0).getTs());
        Assertions.assertEquals("main", result.get(0).getFuncName());
        Assertions.assertEquals("start", result.get(1).getType());
        Assertions.assertEquals(11.6f, result.get(1).getTs());
        Assertions.assertEquals("outer_function", result.get(1).getFuncName());
        Assertions.assertEquals("start", result.get(2).getType());
        Assertions.assertEquals(11.6f, result.get(2).getTs());
        Assertions.assertEquals("inner_function", result.get(2).getFuncName());
        Assertions.assertEquals("end", result.get(3).getType());
        Assertions.assertEquals(12.5f, result.get(3).getTs());
        Assertions.assertEquals("inner_function", result.get(3).getFuncName());
        Assertions.assertEquals("end", result.get(4).getType());
        Assertions.assertEquals(12.5f, result.get(4).getTs());
        Assertions.assertEquals("outer_function", result.get(4).getFuncName());
        Assertions.assertEquals("start", result.get(5).getType());
        Assertions.assertEquals(12.5f, result.get(5).getTs());
        Assertions.assertEquals("new_function", result.get(5).getFuncName());
    }

    @Test
    public void samplesAreAddedToResult_MultipleComplicatedSamplesWithEnds() {
        List<SampleConverter.Event> result = SampleConverter.convertSamplesToEvents(List.of(
                new SampleConverter.Sample(
                        10.7f, new String[] { "main" }),
                new SampleConverter.Sample(
                        11.6f, new String[] { "main", "outer_function", "inner_function" }),
                new SampleConverter.Sample(
                        12.5f, new String[] { "main", "new_function" }),
                new SampleConverter.Sample(
                        10.7f, new String[] { "main", "new_function", "newer_function" }),
                new SampleConverter.Sample(
                        11.6f, new String[] { "main", "outer_function", "inner_function" }),
                new SampleConverter.Sample(
                        12.5f, new String[] { "main", "final_function" }),
                new SampleConverter.Sample(
                        10.7f, new String[] { }),
                new SampleConverter.Sample(
                        11.6f, new String[] { "new_main", "new_outer_function", "new_inner_function" }),
                new SampleConverter.Sample(
                        12.5f, new String[] { "new_main", "new_new_function" })
        ));
        Assertions.assertEquals(22, result.size());
    }

}
