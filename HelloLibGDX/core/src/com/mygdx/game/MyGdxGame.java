package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.actor.MyActor;

public class MyGdxGame extends ApplicationAdapter {
	//画布
	private SpriteBatch batch;
	//纹理
	private Texture texture;
	private Group group;

	private MyActor actor1;

	private MyActor actor2;

	//演员
	private MyActor actor;
	//舞台
	private Stage stage;

	@Override
	public void create () {
		// 创建纹理, badlogic.jpg 图片的宽高为 256 * 256
		texture = new Texture(Gdx.files.internal("badlogic.jpg"));

		// 创建舞台, 舞台宽高默认为屏幕宽高
		stage = new Stage();

		// 创建演员组
		group = new Group();
		// 设置 group 在舞台中的位置
		group.setPosition(50, 100);

		// 添加 group 到舞台（group 本身也是一个演员)
		stage.addActor(group);

		// 创建第一个演员, 并设置属性
		actor1 = new MyActor(new TextureRegion(texture));
		actor1.setScale(0.5F);
		actor1.setPosition(0, 0);   // 位置设置到 group 的左下角

		// 创建第二个演员, 并设置属性
		actor2 = new MyActor(new TextureRegion(texture));
		actor2.setScale(0.25F);
		actor2.setPosition(100, 200);
		actor2.setRotation(45);     // 逆时针旋转 45 度

		// 添加演员到组中
		group.addActor(actor1);
		group.addActor(actor2);

		// 还可以设置演员的绘制顺序（ZIndex 属性）

        /*
         * ZIndex 属性说明:
         *
         * 当组中有许多演员时, 如果某些演员有重叠部分, 往往需要规定演员的绘制顺序, 例如背景先绘制,
         * 人物后绘制, 在 Actor 中使用 ZIndex 属性来表示绘制顺序, ZIndex 值越大越后绘制, 即显示越靠前。
         *
         * ZIndex 属性值其实对应的是 Group 中的演员数组对象中演员所在的索引位置（添加到 Group 中的演员的默认添加到数组的最后位置）。
         * 绘制时 Group 将遍历数组依次绘制演员, 则越靠后（ZIndex/index 越大）的元素越后绘制（即显示越靠前）。
         *
         * 因此, 必须先将 Actor 添加到 Group（或 Stage, 因为 Stage 也是通过 Group 管理其中的演员）中之后才能操作 ZIndex 属性,
         * 否则 ZIndex 属性没有意义。
         *
         * // 获取演员的索引, 如果 actor 没有添加到 Group/Stage 中, 将返回 -1
         * int index = actor.getZIndex();
         *
         * // 设置演员的索引（实际上是将数组中的演员元素移动到指定的索引位置, 可能会引起其他演员的 ZIndex 值跟着改变）
         * // 如果 actor 没有添加到 Group/Stage 中, 设置 ZIndex 属性无效
         * actor.setZIndex(int index);
         */
	}

	@Override
	public void render () {
		// 黑色清屏
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// 更新舞台逻辑
		stage.act();
		// 绘制舞台
		stage.draw();
	}

	@Override
	public void dispose() {
		// 释放资源
		if (texture != null) {
			texture.dispose();
		}
		if (stage != null) {
			stage.dispose();
		}
	}
}
