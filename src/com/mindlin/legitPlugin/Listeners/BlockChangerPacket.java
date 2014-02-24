package com.mindlin.legitPlugin.Listeners;

import net.minecraft.server.v1_7_R1.Block;
import net.minecraft.server.v1_7_R1.PacketDataSerializer;
import net.minecraft.server.v1_7_R1.PacketListener;
import net.minecraft.server.v1_7_R1.PacketPlayOutBlockChange;
import net.minecraft.server.v1_7_R1.PacketPlayOutListener;
import net.minecraft.server.v1_7_R1.World;

import org.bukkit.Location;

public class BlockChangerPacket extends PacketPlayOutBlockChange{
	public int x;
    public int y;
    public int z;
    public int block;
    public int data;

    public BlockChangerPacket() {}

    public BlockChangerPacket(int i, int j, int k, World world) {
        this.x = i;
        this.y = j;
        this.z = k;
        this.block = world.getType(i, j, k).b();
        this.data = world.getData(i, j, k);
    }
    public BlockChangerPacket(int x, int y, int z, Block block, int data) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.block = block.b();
        this.data = data;
    }
    public BlockChangerPacket(int i, int j, int k, int b, int data) {
        this.x = i;
        this.y = j;
        this.z = k;
        this.block = b;
        this.data = data;
    }
    public BlockChangerPacket(Location l, Block b, int data){
    	this(l.getBlockX(), l.getBlockY(), l.getBlockZ(), b, data);
    }
    public BlockChangerPacket(Location l, int b, int data){
    	this(l.getBlockX(), l.getBlockY(), l.getBlockZ(), b, data);
    }
    @Override
    public void a(PacketDataSerializer packetdataserializer) {
        this.x = packetdataserializer.readInt();
        this.y = packetdataserializer.readUnsignedByte();
        this.z = packetdataserializer.readInt();
        this.block = Block.e(packetdataserializer.a()).b();
        this.data = packetdataserializer.readUnsignedByte();
    }
    @Override
    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.x);
        packetdataserializer.writeByte(this.y);
        packetdataserializer.writeInt(this.z);
        packetdataserializer.b(this.block);
        packetdataserializer.writeByte(this.data);
    }
    @Override
    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }
    @Override
    public String b() {
        return String.format("type=%d, data=%d, x=%d, y=%d, z=%d", new Object[] { Integer.valueOf(block), Integer.valueOf(this.data), Integer.valueOf(this.x), Integer.valueOf(this.y), Integer.valueOf(this.z)});
    }
    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
