package com.samiei.globalmap.Interfaces;

public interface IMapClickEvents {
    void onMarkSingleTap(int index,Object object);
    void onMarkLongTap(int index,Object object);
    void onOtherItemsClick();
}
