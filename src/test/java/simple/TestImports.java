package simple;
import io.txcl.mingds.compose.PolygonStream;
import io.txcl.mingds.record.base.GDSIIRecord;
import io.txcl.mingds.stream.GDSIIValidator;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestImports {

    @Test
    public void testPolyPinwheel() throws GDSIIValidator.ValidationException, IOException {
        // Make a Pinwheel (make sure convex polygons work...)
        List<Vector2D> lumpogon =
                IntStream.range(0, 22)
                        .mapToObj(
                                i -> {
                                    if (i % 3 == 0) {
                                        return Vector2D.ZERO;
                                    }
                                    double theta = Math.PI * 2 * i / 20.0;
                                    return new Vector2D(50 * Math.cos(theta), 50 * Math.sin(theta));
                                })
                        .collect(Collectors.toList());

        List<GDSIIRecord<?>> records =
                PolygonStream.ofPolygons(Stream.of(lumpogon)).collect(Collectors.toList());
        GDSIIValidator.validateRecords(records);
        // GDSIIStream.to(Path.of("tempout", "lump.gds"), records.stream());
    }
}
