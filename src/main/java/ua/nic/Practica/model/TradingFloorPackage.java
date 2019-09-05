package ua.nic.Practica.model;

public class TradingFloorPackage {

    private TradingFloorEntity tradingFloorEntity;
    private String imageDir;
    private LocatedEntity locatedEntity;

    @Override
    public String toString () {
        return "TradingFloorPackage{" +
                "tradingFloorEntity=" + tradingFloorEntity +
                ", imageDir='" + imageDir + '\'' +
                ", locatedEntity=" + locatedEntity +
                '}';
    }

    public TradingFloorEntity getTradingFloorEntity () {
        return tradingFloorEntity;
    }

    public void setTradingFloorEntity (TradingFloorEntity tradingFloorEntity) {
        this.tradingFloorEntity = tradingFloorEntity;
    }

    public String getImageDir () {
        return imageDir;
    }

    public void setImageDir (String imageDir) {
        this.imageDir = imageDir;
    }

    public LocatedEntity getLocatedEntity () {
        return locatedEntity;
    }

    public void setLocatedEntity (LocatedEntity locatedEntity) {
        this.locatedEntity = locatedEntity;
    }
}
