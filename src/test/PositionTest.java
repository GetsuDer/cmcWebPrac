package test;

import DAO.PositionDAO;
import logic.Position;
import DAO.Factory;

import java.sql.SQLException;

import org.testng.annotations.Test;
import org.testng.Assert;

public class PositionTest {
    
    @Test
    public void loadPosition() {

        String name = "Test position name";
        String responsibilities = "Test responsibilities";
        Long size = Long.valueOf(2);
        
        Position position = new Position(name, responsibilities, null, size);
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        try {
            positionDAO.addPosition(position);
            Position loadedPosition = positionDAO.getPositionById(position.getId());
            Assert.assertTrue(position.my_equals(loadedPosition));
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

}
