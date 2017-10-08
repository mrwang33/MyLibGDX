package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.actor.MyActor;

public class MyGdxGame extends ApplicationAdapter {
	//画布
	private SpriteBatch batch;
	//纹理
	private Texture acfun;
	//演员
	private MyActor actor;
	//舞台
	private Stage stage;

	@Override
	public void create () {
		acfun = new Texture(Gdx.files.internal("badlogic.jpg"));
		actor = new MyActor(new TextureRegion(acfun));
		 /* 设置演员属性的值 */
		// 设置演员的坐标
		actor.setPosition(50, 100);     // 或者 setX(), setY() 分开设置

		// 设置演员 旋转和缩放支点 为演员的左下角
		actor.setOrigin(0, 0);

		// 设置演员缩放比, X 轴方向缩小到 0.5 倍, Y 轴方向保持不变
		actor.setScale(0.5F, 1.0F);
		// 顺时针旋转 5 度
		actor.setRotation(-5);
		//初始化舞台
		stage = new Stage();
		stage.addActor(actor);
	}

	@Override
	public void render () {
		// 黑色清屏
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// 更新演员逻辑
		actor.act(Gdx.graphics.getDeltaTime());
		//批处理调用每个演员的act
		stage.act();
		//绘制舞台
		stage.draw();
	}

	@Override
	public void dispose() {
		// 应用退出, 纹理不在需要用到, 释放纹理资源
		if (acfun != null) {
			acfun.dispose();
		}
		// 当应用退出时释放资源
		if (stage != null) {
			stage.dispose();
		}
	}
}
