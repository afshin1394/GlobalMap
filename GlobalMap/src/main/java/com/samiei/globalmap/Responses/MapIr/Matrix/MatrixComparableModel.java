package com.samiei.globalmap.Responses.MapIr.Matrix;

public class MatrixComparableModel implements Comparable<MatrixComparableModel>{


    private String distance;

    private String duration;

    private String toIndex;

    public MatrixComparableModel(String distance, String duration, String toIndex) {
        this.distance = distance;
        this.duration = duration;
        this.toIndex = toIndex;
    }


    @Override
    public int compareTo(MatrixComparableModel matrixComparableModel) {
        double duration = Double.parseDouble(this.duration);
        double distance = Double.parseDouble(this.distance);


        if (duration > matrixComparableModel.getDuration()) {
            return 1;
        } else if (duration < matrixComparableModel.getDuration()) {
            return -1;
        } else if (duration == matrixComparableModel.getDuration()) {
            if (distance > matrixComparableModel.getDistance()) {
                return 1;
            } else if (distance < matrixComparableModel.getDistance()) {
                return -1;
            } else if (distance == matrixComparableModel.getDistance()) {
                return 0;
            }
        }
        return 0;
    }


    public double getDistance() {
        return  Double.parseDouble(this.distance);
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return  Double.parseDouble(this.duration);
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getToIndex() {
        return Integer.parseInt(this.toIndex);
    }

    public void setToIndex(String toIndex) {
        this.toIndex = toIndex;
    }
}
