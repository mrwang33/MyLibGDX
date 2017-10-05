package com.mygdx.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 自定义actor类
 */
public class MyActor extends Actor {
    //纹理区域
    private TextureRegion textureRegion;

    public MyActor(TextureRegion textureRegion) {
        super();
        this.textureRegion = textureRegion;
        // 将演员的宽高设置为纹理区域的宽高（必须设置, 否则宽高默认都为 0, 绘制后看不到）
        setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        // 重新设置宽高
        setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
    }

    /**
     * 绘制演员
     *
     * @param batch
     *      纹理画布, 用于绘制演员封装的纹理。SpriteBatch 就是实现了 Batch 接口
     *
     * @param parentAlpha
     *      父节点的透明度, 处理透明度和演员的颜色属性有关, 稍微复杂, 这里暂时先不处理
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        // 如果 region 为 null 或者 演员不可见, 则直接不绘制
        if (textureRegion == null || !isVisible()) {
            return;
        }

        /* 这里选择一个较为复杂的绘制方法进行绘制
        batch.draw(
                region,
                x, y,
                originX, originY,
                width, height,
                scaleX, scaleY,
                rotation
        );*/

        /*
         * 绘制纹理区域
         * 将演员中的 位置(position, 即 X, Y 坐标), 缩放和旋转支点(origin), 宽高尺寸, 缩放比, 旋转角度 应用到绘制中,
         * 最终 batch 会将综合结果绘制到屏幕上
         */
        batch.draw(
                textureRegion,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );
    }

    /**
     * 演员逻辑处理方法
     * @param delta 表示从渲染上一帧开始到现在渲染当前帧的时间间隔, 或者称为渲染的 时间步 / 时间差。单位: 秒
     */
    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
