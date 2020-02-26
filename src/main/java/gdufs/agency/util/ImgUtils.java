package gdufs.agency.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImgUtils {
	
	private static Map<BufferedImage, String> trainMap = null;
	
	public static int isBlack(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() <= 300) {
			return 1;
		}
		return 0;
	}

	public static int isWhite(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() > 300) {
			return 1;
		}
		return 0;
	}

	public static int getColorBright(int colorInt) {
		Color color = new Color(colorInt);
		return color.getRed() + color.getGreen() + color.getBlue();

	}

	public static int isBlackOrWhite(int colorInt) {
		if (getColorBright(colorInt) < 30 || getColorBright(colorInt) > 730) {
			return 1;
		}
		return 0;
	}
	
	public static BufferedImage removeBackgroud(InputStream input)
			throws Exception {
		BufferedImage img = ImageIO.read(input);
		int width = img.getWidth();
		int height = img.getHeight();
		for (int x = 1; x < width - 1; ++x) {
			for (int y = 1; y < height - 1; ++y) {
				if (getColorBright(img.getRGB(x, y)) < 100) {
					if (isBlackOrWhite(img.getRGB(x - 1, y))
							+ isBlackOrWhite(img.getRGB(x + 1, y))
							+ isBlackOrWhite(img.getRGB(x, y - 1))
							+ isBlackOrWhite(img.getRGB(x, y + 1)) == 4) {
						img.setRGB(x, y, Color.WHITE.getRGB());
					}
				}
			}
		}
		for (int x = 1; x < width - 1; ++x) {
			for (int y = 1; y < height - 1; ++y) {
				if (getColorBright(img.getRGB(x, y)) < 100) {
					if (isBlackOrWhite(img.getRGB(x - 1, y))
							+ isBlackOrWhite(img.getRGB(x + 1, y))
							+ isBlackOrWhite(img.getRGB(x, y - 1))
							+ isBlackOrWhite(img.getRGB(x, y + 1)) == 4) {
						img.setRGB(x, y, Color.WHITE.getRGB());
					}
				}
			}
		}
		img = img.getSubimage(1, 1, img.getWidth() - 2, img.getHeight() - 2);
		return img;
	}
	
	public static BufferedImage removeBlank(BufferedImage img) throws Exception {
		int width = img.getWidth();
		int height = img.getHeight();
		int start = 0;
		int end = 0;
		Label1: for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					start = y;
					break Label1;
				}
			}
		}
		Label2: for (int y = height - 1; y >= 0; --y) {
			for (int x = 0; x < width; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					end = y;
					break Label2;
				}
			}
		}
		return img.getSubimage(0, start, width, end - start + 1);
	}
	
	public static List<BufferedImage> splitImage(BufferedImage img)
			throws Exception {
		List<BufferedImage> subImgs = new ArrayList<BufferedImage>();
		int width = img.getWidth();
		int height = img.getHeight();
		List<Integer> weightlist = new ArrayList<Integer>();
		for (int x = 0; x < width; ++x) {
			int count = 0;
			for (int y = 0; y < height; ++y) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
			weightlist.add(count);
		}
		for (int i = 0; i < weightlist.size(); i++) {
			int length = 0;
			while (i < weightlist.size() && weightlist.get(i) > 0) {
				i++;
				length++;
			}
			if (length > 18) {
				subImgs.add(removeBlank(img.getSubimage(i - length, 0,
						length / 2, height)));
				subImgs.add(removeBlank(img.getSubimage(i - length / 2, 0,
						length / 2, height)));
			} else if (length > 5) {
				subImgs.add(removeBlank(img.getSubimage(i - length, 0, length,
						height)));
			}
		}

		return subImgs;
	}
	
	public static Map<BufferedImage, String> loadTrainData() throws Exception {
		if (trainMap == null) {
			Map<BufferedImage, String> map = new HashMap<BufferedImage, String>();
//			File dir = new File("/usr/local/tomcat/apache-tomcat-7.0.94/webapps/train/");
//			String userDir = System.getProperties().getProperty("user.dir");
//			System.out.println(userDir);
//			File dir = new File("/home/train/");
			
//			File dir = new File("train/");

			File dir = new File("E:\\JavaEngineerPerfect\\agency\\train");
//			System.out.println("st");
//			if(!dir.exists()) {
//				dir.mkdir();
////				System.out.println(dir);
//			}

			File[] files = dir.listFiles();
			for (File file : files) {
				map.put(ImageIO.read(file), file.getName().charAt(0) + "");
			}
//			String url="http://120.78.219.119/resources/train/1.jpg";
//			URL input=new URL(url);
//			map.put(ImageIO.read(input),"1");
//			String url2="http://120.78.219.119/resources/train/2.jpg";
//			URL input2=new URL(url2);
//			map.put(ImageIO.read(input2),"2");
//			String url3="http://120.78.219.119/resources/train/3.jpg";
//			URL input3=new URL(url3);
//			map.put(ImageIO.read(input3),"3");
//			String urlb="http://120.78.219.119/resources/train/b.jpg";
//			URL inputb=new URL(urlb);
//			map.put(ImageIO.read(inputb),"b");
//			String urlc="http://120.78.219.119/resources/train/c.jpg";
//			URL inputc=new URL(urlc);
//			map.put(ImageIO.read(inputc),"c");
//			String urlm="http://120.78.219.119/resources/train/m.jpg";
//			URL inputm=new URL(urlm);
//			map.put(ImageIO.read(inputm),"m");
//			String urln="http://120.78.219.119/resources/train/n.jpg";
//			URL inputn=new URL(urln);
//			map.put(ImageIO.read(inputn),"n");
//			String urlv="http://120.78.219.119/resources/train/v.jpg";
//			URL inputv=new URL(urlv);
//			map.put(ImageIO.read(inputv),"v");
//			String urlx="http://120.78.219.119/resources/train/x.jpg";
//			URL inputx=new URL(urlx);
//			map.put(ImageIO.read(inputx),"x");
//			String urlz="http://120.78.219.119/resources/train/z.jpg";
//			URL inputz=new URL(urlz);
//			map.put(ImageIO.read(inputz),"z");
			trainMap = map;
		}
		return trainMap;
	}
	
	public static boolean isNotEight(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int minCount = width;
		for (int y = height / 2 - 2; y < height / 2 + 2; ++y) {
			int count = 0;
			for (int x = 0; x < width / 2 + 2; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
			minCount = Math.min(count, minCount);
		}
		return minCount < 2;
	}

	public static boolean isNotThree(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int minCount = width;
		for (int y = height / 2 - 3; y < height / 2 + 3; ++y) {
			int count = 0;
			for (int x = 0; x < width / 2 + 1; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
			minCount = Math.min(count, minCount);
		}
		return minCount > 0;
	}

	public static boolean isNotFive(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int minCount = width;
		for (int y = 0; y < height / 3; ++y) {
			int count = 0;
			for (int x = width * 2 / 3; x < width; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
			minCount = Math.min(count, minCount);
		}
		return minCount > 0;
	}
		
	public static String getSingleCharOcr(BufferedImage img,
			Map<BufferedImage, String> map) throws Exception {
		String result = "#";
		int width = img.getWidth();
		int height = img.getHeight();
		int min = width * height;
		boolean bNotEight = isNotEight(img);
		boolean bNotThree = isNotThree(img);
		boolean bNotFive = isNotFive(img);
		for (BufferedImage bi : map.keySet()) {
			if (bNotThree && map.get(bi).startsWith("3"))
				continue;
			if (bNotEight && map.get(bi).startsWith("8"))
				continue;
			if (bNotFive && map.get(bi).startsWith("5"))
				continue;
			double count1 = getBlackCount(img);
			double count2 = getBlackCount(bi);
			if (Math.abs(count1 - count2) / Math.max(count1, count2) > 0.25)
				continue;
			int count = 0;
			if (width < bi.getWidth() && height < bi.getHeight()) {
				for (int m = 0; m <= bi.getWidth() - width; m++) {
					for (int n = 0; n <= bi.getHeight() - height; n++) {
						Label1: for (int x = m; x < m + width; ++x) {
							for (int y = n; y < n + height; ++y) {
								if (isWhite(img.getRGB(x - m, y - n)) != isWhite(bi
										.getRGB(x, y))) {
									count++;
									if (count >= min)
										break Label1;
								}
							}
						}
					}
				}
			} else {
				int widthmin = width < bi.getWidth() ? width : bi.getWidth();
				int heightmin = height < bi.getHeight() ? height : bi
						.getHeight();
				Label1: for (int x = 0; x < widthmin; ++x) {
					for (int y = 0; y < heightmin; ++y) {
						if (isWhite(img.getRGB(x, y)) != isWhite(bi
								.getRGB(x, y))) {
							count++;
							if (count >= min)
								break Label1;
						}
					}
				}
			}
			if (count < min) {
				min = count;
				result = map.get(bi);
			}
		}
		return result;
	}
	
	public static int getBlackCount(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int count = 0;
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static String getAllOcr(byte[] buf) throws Exception {
		InputStream in = new ByteArrayInputStream(buf); 
//		System.out.println(in);
		BufferedImage img = removeBackgroud(in);
		List<BufferedImage> listImg = splitImage(img);
		Map<BufferedImage, String> map = loadTrainData();
		String result = "";
		for (BufferedImage bi : listImg) {
			result += getSingleCharOcr(bi, map);
		}
//		System.out.println(result);
		return result;
	}
}
