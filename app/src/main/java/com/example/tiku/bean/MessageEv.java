package com.example.tiku.bean;

public class MessageEv {
    private long count;
    private long contentgh;

    public long getCount() {
        return count;
    }

    public long getContentgh() {
        return contentgh;
    }

    public MessageEv(long count, long contentgh) {
        this.count = count;
        this.contentgh = contentgh;
    }
}
